package top.non.late.pavilion.basic.java.designs.builder.code.simple;

import lombok.Data;

/**
 * Created by nonlatepavilion on 2017/9/8.
 * Desc: 当只有一个concrete builder 时,可以将builder和concrete builder合并。
 * 该例中，更是将builder置为product的内部类,并且省略director,从而使得代码更加简洁。
 */
@Data
public class Book {
    private String author;
    private String bookName;
    private String isbn;

    //private constructor
    private Book(Builder builder) {
        this.author = builder.author;
        this.bookName = builder.bookName;
        this.isbn = builder.isbn;
    }

    public static class Builder {
        private String author;
        private String bookName;
        private String isbn;

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder bookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

    }

}

