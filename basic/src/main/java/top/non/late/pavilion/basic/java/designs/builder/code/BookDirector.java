package top.non.late.pavilion.basic.java.designs.builder.code;

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/7.
 * Desc: 指挥者。包含 builder，用来统一product的生产过程
 */
public class BookDirector {
    private BookBuilder builder;

    public BookDirector(BookBuilder builder) {
        this.builder = builder;
    }

    public BookProduct build() {
        builder.bookName();
        builder.author();
        builder.isbn();
        return builder.build();
    }

}
