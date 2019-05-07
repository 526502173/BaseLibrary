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
import timber.log.Timber

/**
 * @author linzheng
 */
//todo 将 LoadMore 先关的方法抽离出来.使用 LoadMoreStatus 这个类来控制 LoadMore 的显示。
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
        val previousStatus = this.mLoadMoreStatus
        val previousHasLoadMoreItem = hasLoadMoreItem()
        this.mLoadMoreStatus = newLoadMoreStatus
        val hasLoadMoreItem = hasLoadMoreItem()
        mDelegate.loadMoreItem.bind(newLoadMoreStatus)
        if (previousHasLoadMoreItem != hasLoadMoreItem) {
            if (previousHasLoadMoreItem) {
                Timber.d("CommonDiffLoadMoreAdapter => bind() => notifyItemRemoved()")
                notifyItemRemoved(super.getItemCount())
            } else {
                Timber.d("CommonDiffLoadMoreAdapter => bind() => notifyItemInserted()")
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasLoadMoreItem && previousStatus != newLoadMoreStatus) {
            Timber.d("CommonDiffLoadMoreAdapter => bind() => notifyItemChanged()")
            notifyItemChanged(itemCount - 1)
        }
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

    override fun getItemCount() = super.getItemCount() + if (hasLoadMoreItem()) 1 else 0

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
        bind(LoadMoreStatus.LOAD_MORE_NO_MORE)
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