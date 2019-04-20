package com.lz.baselibrary.view.loadmore.paging

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * MultiTypePagedListAdapterWrapper
 * 用于给一个 MultiType 类型的 Adapter 添加  Paging 实现的 LoadMore 功能
 * @author linzheng
 */
class MultiTypePagedListAdapterWrapper(
        private val mWrapperAdapter: MultiTypeAdapter
) : MultiTypePagedListAdapter(), LoadMore {

    private val mListener = object : LoadMoreListener {
        override fun onLoadMore(view: View) {
            //ignore
            //使用 Paging 不需用此方法
        }
    }

    private val mDelegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate(
            this, mWrapperAdapter, LibraryLoadMoreInitialize.sLoadMoreAdapterFactory, mListener
    )

    override fun noMore() = mDelegate.noMore()

    override fun loading() = mDelegate.loading()

    override fun normal() = mDelegate.normal()

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun getItem(position: Int): Any {
        return if (itemCount != 0 && position == items.size) mDelegate.loadMoreItem else super.getItem(position)
    }

    override fun getItemCount(): Int {
        return if (items.isNotEmpty()) items.size + 1 else super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount != 0 && position == items.size)
            LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
        else super.getItemViewType(position)
    }

    override fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        val itemType = holder.itemViewType
        return if (itemType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mDelegate.loadMoreItemViewBinder as ItemViewBinder<Any, RecyclerView.ViewHolder>
        else super.getOutBinderByViewHolder(holder)
    }

    override var types: Types
        get() = mWrapperAdapter.types
        set(value) {}

}