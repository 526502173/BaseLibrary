package com.lz.baselibrary.view.adapter.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.adapter.BaseAdapterWrapper
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreListenerWrapper
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class LoadMoreAdapter(
        override val mWrapperAdapter: MultiTypeAdapter,
        private val mListener: LoadMoreListener
) : BaseAdapterWrapper(mWrapperAdapter), LoadMore {

    override var items: List<Any>
        get() = mWrapperAdapter.items
        set(value) {
            mWrapperAdapter.items = value
        }

    private val mDelegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                mWrapperAdapter,
                LibraryLoadMoreInitialize.sLoadMoreAdapterFactory,
                LoadMoreListenerWrapper(mListener, this)
        )
    }

    override fun getItemViewType(position: Int) = mDelegate.getItemViewType(position)

    override fun getItemCount() = mDelegate.getItemCount()

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) = mDelegate.onBindViewHolder(holder, position, payloads)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = mDelegate.onBindViewHolder(holder, position)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun noMore() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
    }

    override fun loading() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_LOADING)
    }

    override fun normal() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_NORMAL)
    }

    override fun fail(code: Int) {
        setLoadMoreItemStatus(LoadMoreStatus.code(code))
    }

    private fun setLoadMoreItemStatus(status: LoadMoreStatus) {
        mDelegate.loadMoreItem.bind(status)
        notifyItemChanged(if (itemCount > 0) itemCount - 1 else 0)
    }
}