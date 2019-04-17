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
 * @author linzheng
 */
//todo 考虑让 LoadMoreAdapterDelegate 接口继承 LoadMore 接口
class DefaultLoadMoreAdapterDelegate(
        private val mAdapter: MultiTypeAdapter,
        private val mLoadMoreAdapterFactory: LoadMoreAdapterFactory,
        private val mListener: LoadMoreListener
) : LoadMoreAdapterDelegate {

    override val loadMoreItem: LoadMoreItem by lazy {
        LoadMoreItem()
    }

    override val loadMoreItemViewBinder: LoadMoreItemViewBinder
        get() = LoadMoreItemViewBinder(mLoadMoreAdapterFactory, mListener)

    override fun getItemViewType(position: Int) =
            if (mAdapter.itemCount == position) {
                LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
            } else mAdapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder {
        return if (indexViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) {
            val layoutInflater = LayoutInflater.from(parent.context)
            loadMoreItemViewBinder.onCreateViewHolder(layoutInflater, parent)
        } else mAdapter.onCreateViewHolder(parent, indexViewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, loadMoreItem, payloads)
        else
            mAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, loadMoreItem)
        else
            mAdapter.onBindViewHolder(holder, position)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onFailedToRecycleView(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) loadMoreItemViewBinder.onViewAttachedToWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onViewDetachedFromWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onViewRecycled(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else mAdapter.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return if (mAdapter.items.isNotEmpty()) mAdapter.itemCount + 1
        else mAdapter.itemCount
    }

}