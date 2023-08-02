package usts.pycro.mybatisflex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationOneToMany;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-30 9:18 PM
 */
@Data
@Table("tb_account")
public class Account implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String userName;
    private Integer age;
    private Date birthday;
    // 账户拥有的 图书列表
    @RelationOneToMany(selfField = "id", targetField = "accountId")
    private List<Book> books;
    // @RelationOneToMany(selfField = "id", targetField = "accountId", mapKeyField = "id")
    // private Map<String, Book> books;

}
