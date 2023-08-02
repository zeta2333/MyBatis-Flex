package usts.pycro.mybatisflex.entity;

import com.mybatisflex.annotation.ColumnAlias;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Pycro
 * @version 1.0
 * 2023-08-01 1:50 PM
 */
@Data
@Table("tb_book")
public class Book implements Serializable {
    @Id(keyType = KeyType.Auto)
    @ColumnAlias("bookId")
    private Long id;
    private Long accountId;
    @ColumnAlias("bookTitle")
    private String title;
    private String content;
}
