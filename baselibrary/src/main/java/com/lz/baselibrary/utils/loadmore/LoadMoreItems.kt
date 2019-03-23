package com.lz.baselibrary.utils.loadmore

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
 * @property items 源 Items 类型为 ArrayList
 * @property isEnableLoadMore true 表示启用加载更多功能
 * @author linzheng
 */
class LoadMoreItems(
        private val items: ArrayList<Any>
) : MutableList<Any> {

    //是否启用 LoadMore
    private var isEnableLoadMore: Boolean = false

    override val size: Int
        get() = items.size - 1

    override fun add(element: Any) = items.add(element).also {
        initLoadMore()
    }

    override fun add(index: Int, element: Any) = items.add(index, element).also {
        initLoadMore()
    }

    override fun addAll(index: Int, elements: Collection<Any>) = items.addAll(index, elements).also {
        initLoadMore()
    }

    override fun addAll(elements: Collection<Any>): Boolean {
        initLoadMore()
        (items[items.lastIndex] as LoadMoreItem).status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
        return addAll(items.lastIndex, elements)
    }

    override fun clear() = items.clear()

    override fun remove(element: Any) = items.remove(element)

    override fun removeAll(elements: Collection<Any>) = items.removeAll(elements)

    override fun removeAt(index: Int) = items.removeAt(index)

    override fun retainAll(elements: Collection<Any>) = items.retainAll(elements)

    override fun set(index: Int, element: Any) = items.set(index, element)

    override fun contains(element: Any) = items.contains(element)

    override fun containsAll(elements: Collection<Any>) = items.containsAll(elements)

    override fun get(index: Int) = items[index]

    override fun indexOf(element: Any) = items.indexOf(element)

    override fun isEmpty() = items.isEmpty()

    override fun iterator() = items.iterator()

    override fun lastIndexOf(element: Any) = items.lastIndexOf(element)

    override fun listIterator() = items.listIterator()

    override fun listIterator(index: Int) = items.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int) = items.subList(fromIndex, toIndex)

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
