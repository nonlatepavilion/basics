package top.non.late.pavilion.basic.kotlin.designs.builder

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/25.
 * Desc:
 */
class Customer {
    fun run() {
        val proseArticleBuilder = JVMBookBuilder()
        var director = BookDirector(proseArticleBuilder)
        println(director.build().toString())

        val argumentBuilder = SpringCloudBookBuilder()
        director = BookDirector(argumentBuilder)
        println(director.build().toString())
    }
}

fun main(args: Array<String>) {
    Customer().run()
}
