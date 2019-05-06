package com.lz.baselibrary.view.adapter.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.adapter.CommonDiffAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class CommonDiffLoadMoreAdapter<T>(
        override val mWrapperAdapter: MultiTypeAdapter,
        protected open val mListener: LoadMoreListener,
        protected open val mRetry: () -> Unit,
        private val mDelegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate.create(
                mWrapperAdapter,
                LibraryLoadMoreInitialize.sLoadMoreAdapterFactory,
                mListener,
                mRetry
        )
) : CommonDiffAdapter<T>(mWrapperAdapter), LoadMore {

    private var mLoadMoreStatus: LoadMoreStatus? = null

    /**
     * 将 Adapter 和 [LoadMoreStatus] 进行绑定，
     * 更具不同的状态调整 getItemCount() 的返回值以及 LoadMoreItemView 的显示状态
     */
    fun bind(newLoadMoreStatus: LoadMoreStatus) {
        val previousState = this.mLoadMoreStatus
        val hadExtraRow = hasLoadMoreItem()
        this.mLoadMoreStatus = newLoadMoreStatus
        val hasExtraRow = hasLoadMoreItem()
        mDelegate.loadMoreItem.bind(newLoadMoreStatus)
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) notifyItemRemoved(super.getItemCount())
            else notifyItemInserted(super.getItemCount())
        } else if (hasExtraRow && previousState != newLoadMoreStatus)
            notifyItemChanged(itemCount - 1)
    }

    /**
     * 是否需要 LoadMoreItem
     */
    private fun hasLoadMoreItem() = mLoadMoreStatus != null && mLoadMoreStatus != LoadMoreStatus.LOAD_MORE_NORMAL

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun getItem(position: Int): Any? {
        return if (hasLoadMoreItem() && position == items.size) mDelegate.loadMoreItem else super.getItem(position)
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount() + if (hasLoadMoreItem()) 1 else 0
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasLoadMoreItem() && position == itemCount - 1)
            LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
        else super.getItemViewType(position)
    }

    override fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        val itemType = holder.itemViewType
        return if (itemType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mDelegate.loadMoreItemViewBinder as ItemViewBinder<Any, RecyclerView.ViewHolder>
        else super.getOutBinderByViewHolder(holder)
    }

    override fun noMore() {
        bind(LoadMoreStatus.LOAD_MORE_NORMAL)
    }

    override fun loading() {
        bind(LoadMoreStatus.LOAD_MORE_LOADING)
    }

    override fun normal() {
        bind(LoadMoreStatus.LOAD_MORE_NORMAL)
    }

    override fun fail(code: Int) {
        bind(LoadMoreStatus.code(code))
    }

}