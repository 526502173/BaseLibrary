package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.adapter.BaseAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreListenerWrapper
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * LoadMoreAdapter
 * @author linzheng
 */
class LoadMoreAdapter(
        override val mWrapperAdapter: MultiTypeAdapter,
        private val mListener: LoadMoreListener,
        private val mRetry: () -> Unit
) : BaseAdapter(mWrapperAdapter), LoadMore {

    override var items: List<Any>
        get() = mWrapperAdapter.items
        set(value) {
            mWrapperAdapter.items = value
        }

    private val mDelegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                this,
                LibraryLoadMoreInitialize.sLoadMoreAdapterFactory,
                LoadMoreListenerWrapper(mListener, this),
                { items[it] },
                { items.size },
                mRetry
        )
    }

    override fun getItemCount() = mDelegate.getItemCount()

    override fun getItemViewType(position: Int) = mDelegate.getItemViewType(position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        mDelegate.onBindViewHolder(holder, position, payloads)
    }

    override fun noMore() {
        mDelegate.noMore()
    }

    override fun loading() {
        mDelegate.loading()
    }

    override fun disable() {
        mDelegate.disable()
    }

    override fun fail(code: Int) {
        mDelegate.fail(code)
    }

    override fun readly() {
        mDelegate.readly()
    }

}