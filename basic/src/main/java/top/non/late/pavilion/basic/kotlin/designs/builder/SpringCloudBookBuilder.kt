package top.non.late.pavilion.basic.kotlin.designs.builder

/**
 * Created by yuhp@terminus.io on 2017/9/24.
 * Desc:
 */
class SpringCloudBookBuilder : BookBuilder {
    private val product = BookProduct()
    override fun bookName() {
        product.bookName = "Spring Cloud微服务实战"
    }

    override fun author() {
        product.author = "翟永超"
    }

    override fun isbn() {
        product.isbn = "9787121313011"
    }

    override fun build(): BookProduct {
        return product
    }

}