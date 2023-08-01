package usts.pycro.mybatisflex.vo;

import lombok.Data;

/**
 * @author Pycro
 * @version 1.0
 * 2023-08-01 1:50 PM
 */
@Data
public class BookVo {

    // 图书的基本字段
    private Long id;
    private Long accountId;
    private String title;
    private String content;

    // 用户表的字段
    private String userName;
    private int userAge;
}
