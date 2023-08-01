package usts.pycro.mybatisflex.dto;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.ColumnMask;
import com.mybatisflex.core.mask.Masks;
import lombok.Data;

import java.util.Date;

/**
 * @author Pycro
 * @version 1.0
 * 2023-07-31 3:34 PM
 */
@Data
public class ArticleDTO {

    private Long id;
    @Column(value = "account_id", isLogicDelete = true)
    private Long accountId;
    private String title;
    @ColumnMask(value = Masks.BANK_CARD_NUMBER)
    private String content;

    // 名称不匹配
    private String accountName;
    private Integer accountAge;
    private Date accountBirth;
    // Account对象
    // private Account account;
}
