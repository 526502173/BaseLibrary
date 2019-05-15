package com.lz.baselibrary.view.adapter.loadmore

import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * LoadMore + MultiType
 * @author linzheng
 */
class LoadMoreAdapter(
        private val wrapperAdapter: MultiTypeAdapter,
        delegate: LoadMoreAdapterDelegate
) : CommonLoadMoreAdapter(wrapperAdapter, delegate), LoadMore by delegate {

    init {
        delegate.adapter = this
    }

    override var items: List<Any>
        get() = wrapperAdapter.items
        set(value) {
            wrapperAdapter.items = value
        }

    override fun getDataItem(position: Int) = items[position]

    override fun getDataItemCount() = items.size


}