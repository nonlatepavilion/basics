package top.non.late.pavilion.basic.kotlin.designs.builder

/**
 * Created by yuhp@terminus.io on 2017/9/14.
 * Desc:
 */
interface BookBuilder {
    fun bookName()
    fun author()
    fun isbn()
    fun build(): BookProduct
}