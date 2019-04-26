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
class DefaultLoadMoreAdapterDelegate private constructor(
        private val wrapperAdapter: MultiTypeAdapter,
        private val loadMoreAdapterFactory: LoadMoreAdapterFactory,
        private val listener: LoadMoreListener,
        private val retry: () -> Unit
) : LoadMoreAdapterDelegate {

    override val loadMoreItem: LoadMoreItem by lazy {
        LoadMoreItem()
    }

    override val loadMoreItemViewBinder: LoadMoreItemViewBinder by lazy {
        LoadMoreItemViewBinder(loadMoreAdapterFactory, listener, retry)
    }

    override fun getItemViewType(position: Int) =
            if (wrapperAdapter.itemCount == position) {
                LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
            } else wrapperAdapter.getItemViewType(position)

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder {
        return if (indexViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) {
            val layoutInflater = LayoutInflater.from(parent.context)
            loadMoreItemViewBinder.onCreateViewHolder(layoutInflater, parent)
        } else wrapperAdapter.onCreateViewHolder(parent, indexViewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, loadMoreItem, payloads)
        else
            wrapperAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, loadMoreItem)
        else
            wrapperAdapter.onBindViewHolder(holder, position)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onFailedToRecycleView(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else wrapperAdapter.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE) loadMoreItemViewBinder.onViewAttachedToWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else wrapperAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onViewDetachedFromWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else wrapperAdapter.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            loadMoreItemViewBinder.onViewRecycled(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else wrapperAdapter.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return if (wrapperAdapter.items.isNotEmpty()) wrapperAdapter.itemCount + 1
        else wrapperAdapter.itemCount
    }

    companion object {
        fun create(
                wrapperAdapter: MultiTypeAdapter,
                loadMoreAdapterFactory: LoadMoreAdapterFactory,
                listener: LoadMoreListener,
                retry: () -> Unit = {}
        ): LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate(
                wrapperAdapter,
                loadMoreAdapterFactory,
                listener,
                retry
        )
    }

}