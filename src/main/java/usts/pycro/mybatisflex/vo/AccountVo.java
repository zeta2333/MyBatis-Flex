package usts.pycro.mybatisflex.vo;

import com.mybatisflex.annotation.RelationOneToMany;
import lombok.Data;
import usts.pycro.mybatisflex.entity.Book;

import java.util.List;

/**
 * @author Pycro
 * @version 1.0
 * 2023-08-01 2:04 PM
 */
@Data
public class AccountVo {

    private Long id;
    private String userName;
    private int age;

    //账户拥有的 图书列表
    @RelationOneToMany(selfField = "id",targetField = "accountId")
    private List<Book> books;
}