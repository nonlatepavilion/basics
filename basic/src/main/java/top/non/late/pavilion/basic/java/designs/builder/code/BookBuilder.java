package top.non.late.pavilion.basic.java.designs.builder.code;

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/7.
 * Desc: 一般用接口定义创建一个product所需要的各个操作<br/>
 * 该示例中，用于指定book的bookName，author，isbn
 */
public interface BookBuilder {

    void bookName();

    void author();

    void isbn();

    BookProduct build();

}
