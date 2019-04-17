package com.lz.baselibrary.view.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * 基于 MultiTypeAdapter 实现的加载跟多
 * @author linzheng
 */
class LoadMoreAdapterWrapper(
        private val mAdapter: MultiTypeAdapter,
        mListener: LoadMoreListener
) : MultiTypeAdapter(), LoadMore {

    private val mDelegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate(
            mAdapter, LibraryLoadMoreInitialize.sLoadMoreAdapterFactory, mListener
    )

    override var items: List<Any>
        get() = mAdapter.items
        set(value) {
            mAdapter.items = value
        }

    override var types: Types
        get() = mAdapter.types
        set(value) {
            mAdapter.types = value
        }


    override fun noMore() {
        mDelegate.loadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NO_MORE
        notifyItemChanged(mAdapter.items.size)
    }

    override fun loading() {
        mDelegate.loadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_LOADING
        notifyItemChanged(mAdapter.items.size)
    }

    override fun normal() {
        mDelegate.loadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
    }

    override fun getItemViewType(position: Int) = mDelegate.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) = mDelegate.onBindViewHolder(holder, position, payloads)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = mDelegate.onBindViewHolder(holder, position)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun getItemCount() = mDelegate.getItemCount()


}