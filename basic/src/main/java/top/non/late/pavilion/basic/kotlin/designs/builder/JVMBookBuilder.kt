package top.non.late.pavilion.basic.kotlin.designs.builder

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/25.
 * Desc: 
 */
class JVMBookBuilder : BookBuilder {
    private val product = BookProduct()
    override fun bookName() {
        product.bookName = "深入理解Java虚拟机"
    }

    override fun author() {
        product.author = "周志明"
    }

    override fun isbn() {
        product.isbn = "9787111421900"
    }

    override fun build(): BookProduct {
        return product
    }
}