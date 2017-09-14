package top.non.late.pavilion.basic.java.designs.builder.code.simple;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yuhp@terminus.io on 2017/9/8.
 * Desc:
 */
@Data
@AllArgsConstructor
public class Book {
    private String author;
    private String bookName;
    private String isbn;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String author;
        private String bookName;
        private String isbn;

        private Builder() {

        }

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
            return new Book(author, bookName, isbn);
        }

    }

}

