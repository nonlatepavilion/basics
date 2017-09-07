package top.non.late.pavilion.basic.java.designs.builder.code;

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/7.
 * Desc: jvm book Builder
 */
public class JVMBookBuilder implements BookBuilder {
    private BookProduct product;
    public JVMBookBuilder() {
        this.product = new BookProduct();
    }
    @Override
    public void bookName() {
        this.product.setBookName("深入理解Java虚拟机");
    }

    @Override
    public void author() {
        this.product.setAuthor("周志明");
    }

    @Override
    public void isbn() {
        this.product.setIsbn("9787111421900");
    }

    @Override
    public BookProduct build() {
        return product;
    }
}
