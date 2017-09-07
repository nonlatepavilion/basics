package top.non.late.pavilion.basic.java.designs.builder.code;

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/7.
 * Desc:
 */
public class SpringCloudBookBuilder implements BookBuilder {
    private BookProduct product;

    public SpringCloudBookBuilder() {
        this.product = new BookProduct();
    }


    @Override
    public void bookName() {
        this.product.setBookName("Spring Cloud微服务实战");
    }

    @Override
    public void author() {
        this.product.setAuthor("翟永超");
    }

    @Override
    public void isbn() {
        this.product.setIsbn("9787121313011");
    }

    @Override
    public BookProduct build() {
        return product;
    }
}
