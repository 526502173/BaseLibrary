package com.lz.baselibrary.view.itemdecoration.loadmore

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
            field = value
        }

    override fun noMore() {
        val loadMoreItems = getLoadMoreItems()
        val loadMoreItem = loadMoreItems.getLoadMoreItem()
        loadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NO_MORE
        notifyItemChanged(loadMoreItems.getLoadMoreItemIndex())
    }


    override fun loading() {

    }

    override fun normal() {

    }


    init {
        register(LoadMoreItem::class, LoadMoreItemViewBinder())
        LoadMoreItemViewBinder.sLoadMoreListener = loadMoreListener
    }

    private fun getLoadMoreItems() = items as LoadMoreItems

    override fun getItemCount(): Int {
        return if (isEnableLoadMore) items.size + 1 else items.size
    }

}
