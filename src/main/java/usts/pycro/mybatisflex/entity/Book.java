package usts.pycro.mybatisflex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author Pycro
 * @version 1.0
 * 2023-08-01 1:50 PM
 */
@Data
@Table("tb_book")
public class Book {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long accountId;
    private String title;
    private String content;
}
