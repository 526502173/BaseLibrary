package com.lz.baselibrary.utils.loadmore

import com.lz.baselibrary.multitype.LoadMoreItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class LoadMoreAdapter(
        loadMoreListener: LoadMoreListener
) : MultiTypeAdapter(), LoadMore {

    var isEnableLoadMore: Boolean = true
        set(value) {
            getLoadMoreItems().setEnableLoadMore(value)
        }

    override fun noMore() {
        val loadMoreItems = getLoadMoreItems()
        val loadMoreItem = loadMoreItems.getLoadMoreItem()
        loadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NO_MORE
        notifyItemChanged(loadMoreItems.getLoadMoreItemIndex())
    }

    init {
        register(LoadMoreItem::class, LoadMoreItemViewBinder())
        LoadMoreItemViewBinder.sLoadMoreListener = loadMoreListener
    }

    private fun getLoadMoreItems() = items as LoadMoreItems

    override fun getItemCount(): Int {
        val loadMoreItems = items as LoadMoreItems
        return if (isEnableLoadMore) loadMoreItems.size + 1 else loadMoreItems.size
    }

}
