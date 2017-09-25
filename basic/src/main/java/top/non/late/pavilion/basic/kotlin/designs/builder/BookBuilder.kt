package top.non.late.pavilion.basic.kotlin.designs.builder

/**
 * Created by nonlatepavilion@outlook.com on 2017/9/25.
 * Desc:
 */
interface BookBuilder {
    fun bookName()
    fun author()
    fun isbn()
    fun build(): BookProduct
}