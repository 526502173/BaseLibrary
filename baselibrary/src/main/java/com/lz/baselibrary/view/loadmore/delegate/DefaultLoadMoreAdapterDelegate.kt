package com.lz.baselibrary.view.loadmore.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreItemViewBinder
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import me.drakeet.multitype.MultiTypeAdapter

/**
 * DefaultLoadMoreAdapterDelegate
 * @author linzheng
 */
//todo 添加 create() 方法
class DefaultLoadMoreAdapterDelegate(
        private val mSourceAdapter: MultiTypeAdapter,
        private val mWrapperAdapter: MultiTypeAdapter,
        private val mLoadMoreAdapterFactory: LoadMoreAdapterFactory,
        private val mListener: LoadMoreListener
) : LoadMoreAdapterDelegate {

    override val loadMoreItem: LoadMoreItem by lazy {
        LoadMoreItem()
    }

    override val loadMoreItemViewBinder: LoadMoreItemViewBinder
        get() = LoadMoreItemViewBinder(mLoadMoreAdapterFactory, mListener)

    override fun getItemViewType(position: Int) =
            if (mWrapperAdapter.itemCount == position) {
                LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
            } else mWrapperAdapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder {
        return if (indexViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) {
            val layoutInflater = LayoutInflater.from(parent.context)
            loadMoreItemViewBinder.onCreateViewHolder(layoutInflater, parent)
        } else mWrapperAdapter.onCreateViewHolder(parent, indexViewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, loadMoreItem, payloads)
        else
            mWrapperAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, loadMoreItem)
        else
            mWrapperAdapter.onBindViewHolder(holder, position)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onFailedToRecycleView(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mWrapperAdapter.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) loadMoreItemViewBinder.onViewAttachedToWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mWrapperAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onViewDetachedFromWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mWrapperAdapter.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onViewRecycled(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mWrapperAdapter.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return if (mWrapperAdapter.items.isNotEmpty()) mWrapperAdapter.itemCount + 1
        else mWrapperAdapter.itemCount
    }

    override fun noMore() {
        updateLoadMoreItemIfNeed(LoadMoreItem.LOAD_MORE_STATUS_NO_MORE)
    }

    override fun loading() {
        updateLoadMoreItemIfNeed(LoadMoreItem.LOAD_MORE_STATUS_LOADING)
    }

    override fun normal() {
        updateLoadMoreItemIfNeed(LoadMoreItem.LOAD_MORE_STATUS_NORMAL)
    }

    private fun updateLoadMoreItemIfNeed(status: Int) {
        if (status != loadMoreItem.status) {
            loadMoreItem.status = status
            mSourceAdapter.notifyItemChanged(mSourceAdapter.items.size)
        }
    }

}