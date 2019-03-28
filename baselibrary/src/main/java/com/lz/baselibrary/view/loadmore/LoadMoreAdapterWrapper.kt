package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.multitype.LoadMoreItemViewBinder
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * 基于 MultiTypeAdapter 实现的加载跟多
 * @author linzheng
 */
class LoadMoreAdapterWrapper(
        private val mAdapter: MultiTypeAdapter,
        private val mLoadMoreListener: LoadMoreListener
) : MultiTypeAdapter(), LoadMore {

    /**
     * LoadMoreItem 对象
     */
    private val mLoadMoreItem: LoadMoreItem by lazy { LoadMoreItem() }

    /**
     * LoadMoreItemViewBinder 对象
     */
    private val mLoadMoreItemViewBinder by lazy { LoadMoreItemViewBinder(mLoadMoreListener) }

    init {
        register(LoadMoreItem::class, mLoadMoreItemViewBinder)
    }

    override fun noMore() {
        mLoadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NO_MORE
        notifyItemChanged(items.size)
    }

    override fun loading() {
        mLoadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_LOADING
        notifyItemChanged(items.size)
    }

    override fun normal() {
        mLoadMoreItem.status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.size == position) {
            ITEM_TYPE_LOAD_MORE
        } else mAdapter.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder {
        return if (indexViewType == ITEM_TYPE_LOAD_MORE) {
            val layoutInflater = LayoutInflater.from(parent.context)
            mLoadMoreItemViewBinder.onCreateViewHolder(layoutInflater, parent)
        } else mAdapter.onCreateViewHolder(parent, indexViewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (holder.itemViewType == ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onBindViewHolder(holder as LoadMoreItemViewBinder.LoadMoreViewHolder, mLoadMoreItem, payloads)
        else
            mAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == ITEM_TYPE_LOAD_MORE) mLoadMoreItemViewBinder.onViewAttachedToWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onViewDetachedFromWindow(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else super.onViewDetachedFromWindow(holder)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == ITEM_TYPE_LOAD_MORE)
            mLoadMoreItemViewBinder.onViewRecycled(holder as LoadMoreItemViewBinder.LoadMoreViewHolder)
        else super.onViewRecycled(holder)
    }


    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) mAdapter.itemCount + 1
        else mAdapter.itemCount
    }

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

    companion object {
        private const val ITEM_TYPE_LOAD_MORE = 233331
    }

}