package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.adapter.CommonDiffAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class CommonDiffLoadMoreAdapter<T>(
        override val mWrapperAdapter: MultiTypeAdapter,
        open val mListener: LoadMoreListener,
        open val mRetry: () -> Unit
) : CommonDiffAdapter<T>(mWrapperAdapter), LoadMore {

    private val mDelegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                this,
                LibraryLoadMoreInitialize.sLoadMoreAdapterFactory,
                mListener,
                { mDiffer.getItem(it) },
                { mDiffer.getItemCount() },
                mRetry
        )
    }

    override fun getItemCount(): Int {
        return mDelegate.getItemCount()
    }

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

    override fun readly() {
        mDelegate.readly()
    }

    override fun fail(code: Int) {
        mDelegate.fail(code)
    }
}