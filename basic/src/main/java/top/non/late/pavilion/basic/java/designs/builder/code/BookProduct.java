package top.non.late.pavilion.basic.java.designs.builder.code;

import lombok.Data;
import lombok.ToString;

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/7.
 * Desc: 待生产产品 书 包含 bookName,author,isbn
 */
@Data
@ToString
public class BookProduct {
    private String bookName;
    private String author;
    private String isbn;
}
