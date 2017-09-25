package top.non.late.pavilion.basic.kotlin.designs.builder

/**
 * Created by yuhp@terminus.io on 2017/9/24.
 * Desc:
 */
class BookDirector(private val builder: BookBuilder) {

    fun build(): BookProduct {
        builder.bookName()
        builder.author()
        builder.isbn()
        return builder.build()
    }
}
