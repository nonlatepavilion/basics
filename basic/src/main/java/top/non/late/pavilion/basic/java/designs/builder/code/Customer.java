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

        Book simpleBook1 = new Book.Builder().author("nonlate1").bookName("茶花").isbn("12345678").build();
        Book simpleBook2 = new Book.Builder().author("nonlate2").bookName("兰花").isbn("12345666").build();
        System.out.println(simpleBook1.toString());
        System.out.println(simpleBook2.toString());
    }
}
