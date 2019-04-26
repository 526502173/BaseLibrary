package com.lz.baselibrary.view.loadmore.paging

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
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
        private val wrapperAdapter: MultiTypeAdapter,
        private val retry: () -> Unit
) : MultiTypePagedListAdapter() {

    private val mListener = object : LoadMoreListener {
        override fun onLoadMore(view: View) {
            //ignore
            //使用 Paging 不需用此方法
        }
    }

    private val mDelegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                wrapperAdapter, LibraryLoadMoreInitialize.sLoadMoreAdapterFactory, mListener, retry
        )
    }

    /**
     * 将 Adapter 和 [LoadMoreStatus] 进行绑定，
     * 更具不同的状态调整 getItemCount() 的返回值
     */
    fun bind(newLoadMoreStatus: LoadMoreStatus) {
        val previousState = this.mLoadMoreStatus
        val hadExtraRow = hasExtraRow()
        this.mLoadMoreStatus = newLoadMoreStatus
        val hasExtraRow = hasExtraRow()
        mDelegate.loadMoreItem.bind(newLoadMoreStatus)
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) notifyItemRemoved(super.getItemCount())
            else notifyItemInserted(super.getItemCount())
        } else if (hasExtraRow && previousState != newLoadMoreStatus)
            notifyItemChanged(itemCount - 1)
    }

    private fun hasExtraRow() = mLoadMoreStatus != null && mLoadMoreStatus != LoadMoreStatus.LOAD_MORE_NORMAL

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun getItem(position: Int): Any {
        return if (itemCount != 0 && position == items.size) mDelegate.loadMoreItem else super.getItem(position)
    }

    private var mLoadMoreStatus: LoadMoreStatus? = null

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1)
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
        get() = wrapperAdapter.types
        set(value) {
            wrapperAdapter.types = value
        }

}