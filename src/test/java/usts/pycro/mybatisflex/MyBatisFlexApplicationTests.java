package usts.pycro.mybatisflex;

import com.alibaba.fastjson2.JSON;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.relation.RelationManager;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.core.update.UpdateWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import usts.pycro.mybatisflex.dto.ArticleDTO;
import usts.pycro.mybatisflex.entity.Account;
import usts.pycro.mybatisflex.mapper.AccountMapper;
import usts.pycro.mybatisflex.mapper.BookMapper;
import usts.pycro.mybatisflex.vo.AccountVo;
import usts.pycro.mybatisflex.vo.BookVo;

import java.util.Date;
import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.avg;
import static com.mybatisflex.core.query.QueryMethods.max;
import static usts.pycro.mybatisflex.entity.table.AccountTableDef.ACCOUNT;
import static usts.pycro.mybatisflex.entity.table.ArticleTableDef.ARTICLE;
import static usts.pycro.mybatisflex.entity.table.BookTableDef.BOOK;

@SpringBootTest
class MyBatisFlexApplicationTests {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BookMapper bookMapper;

    @Test
    void contextLoads() {
        QueryWrapper query = QueryWrapper.create()
                .where(ACCOUNT.USER_NAME.like("三"))
                .and(ACCOUNT.AGE.gt(10))
                .and(ACCOUNT.BIRTHDAY.lt(new Date(System.currentTimeMillis())).or(ACCOUNT.ID.eq(2)));

        List<Account> accounts = accountMapper.selectListByQuery(query);
        accounts.forEach(System.out::println);

    }

    @Test
    public void testCustomizeQuery() {
        Account account = accountMapper.selectByName("张三");
        System.out.println(account);
    }

    @Test
    public void testPage() {
        Page<Account> pageParam = new Page<>(1, 2);
        QueryWrapper query = QueryWrapper.create()
                .select()
                .where(ACCOUNT.AGE.gt(10));
        Page<Account> pageModel = accountMapper.paginate(pageParam, query);
        System.out.println(pageModel);
    }

    @Test
    public void testUpdate() {
        Account account = UpdateEntity.of(Account.class, 100);
        account.setUserName("acc1");
        account.setAge(null);
        UpdateWrapper wrapper = UpdateWrapper.of(account);
        wrapper.set(ACCOUNT.AGE, QueryWrapper.create().select(ARTICLE.ID).from(ARTICLE).limit(1));
        accountMapper.update(account);
    }

    @Test
    public void testUpdateChain() {
        UpdateChain.of(Account.class)
                .set(ACCOUNT.USER_NAME, "pycro")
                .setRaw(ACCOUNT.AGE, ACCOUNT.AGE.add(1))
                .where(ACCOUNT.ID.eq(1))
                .update();
    }

    @Test
    public void testDTO() {
        QueryWrapper query = QueryWrapper.create()
                .select(ARTICLE.ALL_COLUMNS)
                .select(ACCOUNT.USER_NAME.as(ArticleDTO::getAccountName),
                        ACCOUNT.AGE.as(ArticleDTO::getAccountAge),
                        ACCOUNT.BIRTHDAY.as(ArticleDTO::getAccountBirth))
                .from(ARTICLE)
                .leftJoin(ACCOUNT).on(ARTICLE.ACCOUNT_ID.eq(ACCOUNT.ID))
                .where(ACCOUNT.ID.ge(0));

        List<ArticleDTO> results = accountMapper.selectListByQueryAs(query, ArticleDTO.class);
        results.forEach(System.out::println);

    }

    @Test
    public void testChain1() {
        System.out.println(QueryChain.of(accountMapper)
                .select(
                        ACCOUNT.ALL_COLUMNS,
                        max(ACCOUNT.AGE).as("max_age"),
                        avg(ACCOUNT.AGE).as("avg_age")
                ).where(ACCOUNT.ID.ge(1))
                .listAs(Object.class));
    }

    @Test
    public void testBook() {
        List<BookVo> bookVos = QueryChain.of(bookMapper)
                .select(
                        // BOOK.ALL_COLUMNS, // 图书的所有字段
                        // ACCOUNT.USER_NAME, // 用户表的 user_name 字段
                        // ACCOUNT.AGE.as("userAge") // 用户表的 age 字段， as "userAge"
                        BOOK.DEFAULT_COLUMNS,
                        ACCOUNT.DEFAULT_COLUMNS
                ).from(BOOK)
                .leftJoin(ACCOUNT).on(BOOK.ACCOUNT_ID.eq(ACCOUNT.ID))
                .where(ACCOUNT.ID.ge(1))
                .listAs(BookVo.class);
        bookVos.forEach(System.out::println);
    }

    @Test
    public void testAccountVo() {
        List<AccountVo> accountVos = QueryChain.of(accountMapper)
                .select(
                        ACCOUNT.DEFAULT_COLUMNS,
                        BOOK.DEFAULT_COLUMNS
                ).from(ACCOUNT)
                .leftJoin(BOOK).on(ACCOUNT.ID.eq(BOOK.ACCOUNT_ID))
                .where(ACCOUNT.ID.ge(1))
                .listAs(AccountVo.class);
        accountVos.forEach(System.out::println);
    }

    @Test
    public void testOneToMany() {
        List<Account> accounts = accountMapper.selectAllWithRelations();
        accounts.forEach(System.out::println);
        RelationManager.addIgnoreRelations("Account.books");
        // accountMapper.selectListWithRelationsByQuery(null);
        System.out.println(JSON.toJSONString(accounts));
    }

    @Test
    public void testFieldQuery() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select().from(ACCOUNT)
                .where(ACCOUNT.ID.ge(1));
        List<Account> accounts = accountMapper.selectListByQuery(queryWrapper,
                accountFieldQueryBuilder ->
                        accountFieldQueryBuilder.field(Account::getBooks)
                                .queryWrapper(account -> QueryWrapper.create()
                                        .select().from(BOOK)
                                        .where(BOOK.ACCOUNT_ID.eq(account.getId())))
        );
        System.out.println(JSON.toJSONString(accounts));
    }

    @Test
    public void testChain2() {
        List<Account> accountList = QueryChain.of(accountMapper)
                .select(ACCOUNT.ALL_COLUMNS)
                .from(ACCOUNT)
                .where(ACCOUNT.ID.ge(1))
                .list();
        accountList.forEach(System.out::println);
    }
}

