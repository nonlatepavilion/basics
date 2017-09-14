package top.non.late.pavilion.basic.java.designs.builder.code;

import top.non.late.pavilion.basic.java.designs.builder.code.simple.Book;

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/7.
 * Desc: 客户端 根据个人喜好生成book.
 */
public class Customer {

    public static void main(String[] args) {

        //jvm
        JVMBookBuilder proseArticleBuilder = new JVMBookBuilder();
        BookDirector director = new BookDirector(proseArticleBuilder);
        System.out.println(director.build().toString());

        //spring cloud
        SpringCloudBookBuilder argumentBuilder = new SpringCloudBookBuilder();
        director = new BookDirector(argumentBuilder);
        System.out.println(director.build().toString());

        Book book = Book.builder().author("asdf").bookName("book name").isbn("12345678").build();
        System.out.println(book.toString());
    }
}
