package com.lz.baselibrary.view.itemdecoration.loadmore

import com.lz.baselibrary.LoadMoreItemNotFoundException

/**
 *
 * 借住 MultiType 在 ArrayList 的基础上实现一个可以加载跟多的 List
 *
 * [LoadMoreItem] 始终位于数组的最后一位，为了不影响其他的操作，
 * 这里重写 size，让 size 返回的大小始终是原 size - 1。例子如下:
 * [数据,数据,数据,数据,数据,LoadMoreItem]
 * lastIndex = 4
 * size = 6
 *
 * @property isEnableLoadMore true 表示启用加载更多功能
 * @author linzheng
 */
class LoadMoreItems : ArrayList<Any>() {

    //是否启用 LoadMore
    private var isEnableLoadMore: Boolean = false

    override val size: Int
        get() = if (isEnableLoadMore && isNotEmpty()) super.size - 1 else super.size

    override fun add(element: Any): Boolean {
        initLoadMore()
        return if (isEnableLoadMore) {
            getLoadMoreItem().status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
            super.add(getLoadMoreItemIndex(), element)
            true
        } else super.add(element)
    }

    override fun add(index: Int, element: Any) = super.add(index, element).also {
        initLoadMore()
        //todo 待实现
    }

    override fun addAll(index: Int, elements: Collection<Any>) = super.addAll(index, elements).also {
        initLoadMore()
        //todo 待实现
    }

    override fun addAll(elements: Collection<Any>): Boolean {
        initLoadMore()
        return if (isEnableLoadMore) {
            getLoadMoreItem().status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
            super.addAll(getLoadMoreItemIndex(), elements)
        } else super.addAll(elements)
    }


    private fun initLoadMore() {
        if (!isHaveLoadMore() && isEnableLoadMore) add(LoadMoreItem())
    }

    private fun isHaveLoadMore() = any { it is LoadMoreItem }

    fun getLoadMoreItemIndex() = if (isEnableLoadMore) lastIndex + 1 else -1

    fun getLoadMoreItem() = if (isEnableLoadMore) get(getLoadMoreItemIndex()) as LoadMoreItem
    else throw LoadMoreItemNotFoundException()

    fun setEnableLoadMore(isEnableLoadMore: Boolean) {
        this.isEnableLoadMore = isEnableLoadMore
    }

}
