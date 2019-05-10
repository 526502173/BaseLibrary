package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.adapter.BaseAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreListenerWrapper
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreDelegateCallback
import me.drakeet.multitype.MultiTypeAdapter

/**
 * LoadMoreAdapter
 * @author linzheng
 */
class LoadMoreAdapter(
        override val wrapperAdapter: MultiTypeAdapter,
        private val mListener: LoadMoreListener,
        private val retry: () -> Unit
) : BaseAdapter(wrapperAdapter), LoadMore, LoadMoreDelegateCallback {

    override var items: List<Any>
        get() = wrapperAdapter.items
        set(value) {
            wrapperAdapter.items = value
        }

    private val delegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                this,
                LoadMoreListenerWrapper(mListener, this),
                this,
                retry
        )
    }

    override fun getItemCount() = delegate.getItemCount()

    override fun getItemViewType(position: Int) = delegate.getItemViewType(position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        delegate.onBindViewHolder(holder, position, payloads)
    }

    override fun noMore() {
        delegate.noMore()
    }

    override fun loading() {
        delegate.loading()
    }

    override fun disable() {
        delegate.disable()
    }

    override fun fail(code: Int) {
        delegate.fail(code)
    }

    override fun readly() {
        delegate.readly()
    }

    override fun getDataItem(position: Int) = items[position]

    override fun getDataItemCount() = items.size

}