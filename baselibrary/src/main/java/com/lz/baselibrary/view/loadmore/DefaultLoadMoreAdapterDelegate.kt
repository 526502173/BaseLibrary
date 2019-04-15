package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DefaultLoadMoreAdapterDelegate(
        private val mAdapter: MultiTypeAdapter,
        mLoadMoreAdapterFactory: LoadMoreAdapterFactory,
        mListener: LoadMoreListener
) : LoadMoreAdapterDelegate, LoadMore {

    private val mLoadMoreItemViewBinder = LoadMoreItemViewBinder(mLoadMoreAdapterFactory, mListener)

    private val mLoadMoreItem = LoadMoreItem()

    init {
        mAdapter.register(LoadMoreItem::class, mLoadMoreItemViewBinder)
    }

    override fun getItemViewType(position: Int) = if (mAdapter.itemCount - 1 == position) {
        LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
    } else mAdapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder {
        return if (indexViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) {
            val layoutInflater = LayoutInflater.from(parent.context)
            mLoadMoreItemViewBinder.onCreateViewHolder(layoutInflater, parent)
        } else mAdapter.onCreateViewHolder(parent, indexViewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, mLoadMoreItem, payloads)
        else
            mAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, mLoadMoreItem)
        else
            mAdapter.onBindViewHolder(holder, position)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onFailedToRecycleView(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) mLoadMoreItemViewBinder.onViewAttachedToWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onViewDetachedFromWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onViewRecycled(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return if (mAdapter.items.isNotEmpty()) mAdapter.itemCount + 1
        else mAdapter.itemCount
    }

    override fun noMore() {
        mLoadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NO_MORE
        mAdapter.notifyItemChanged(mAdapter.items.size)
    }

    override fun loading() {
        mLoadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_LOADING
        mAdapter.notifyItemChanged(mAdapter.items.size)
    }

    override fun normal() {
        mLoadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
    }

}