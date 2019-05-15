package com.lz.baselibrary.view.adapter.loadmore

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.submit.SubmitDelegate
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * CommonDiffLoadMoreAdapter
 * @author linzheng
 */
open class CommonDiffLoadMoreAdapter<T>(
        wrapperAdapter: MultiTypeAdapter,
        delegate: LoadMoreAdapterDelegate,
        private val differ: Differ<T>
) : CommonLoadMoreAdapter(wrapperAdapter, delegate), LoadMore by delegate, SubmitDelegate<T> by differ {

    override var items: List<Any>
        get() = differ.currentList
        set(value) {}

    override fun getDataItem(position: Int) = differ.getItem(position)

    override fun getDataItemCount() = differ.getItemCount()
}