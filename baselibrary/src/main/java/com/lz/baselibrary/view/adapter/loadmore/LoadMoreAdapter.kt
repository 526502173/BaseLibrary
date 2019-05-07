package com.lz.baselibrary.view.adapter.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.adapter.BaseAdapterWrapper
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreListenerWrapper
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import timber.log.Timber

/**
 * @author linzheng
 */
class LoadMoreAdapter(
        override val mWrapperAdapter: MultiTypeAdapter,
        private val mListener: LoadMoreListener
) : BaseAdapterWrapper(mWrapperAdapter), LoadMore {

    override var items: List<Any>
        get() = mWrapperAdapter.items
        set(value) {
            mWrapperAdapter.items = value
        }

    private val mDelegate: LoadMoreAdapterDelegate by lazy {
        DefaultLoadMoreAdapterDelegate.create(
                mWrapperAdapter,
                LibraryLoadMoreInitialize.sLoadMoreAdapterFactory,
                LoadMoreListenerWrapper(mListener, this)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int) = mDelegate.onCreateViewHolder(parent, indexViewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = mDelegate.onBindViewHolder(holder, position)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) = mDelegate.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) = mDelegate.onViewDetachedFromWindow(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) = mDelegate.onViewRecycled(holder)

    override fun noMore() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
    }

    override fun loading() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_LOADING)
    }

    override fun normal() {
        setLoadMoreItemStatus(LoadMoreStatus.LOAD_MORE_NORMAL)
    }

    override fun fail(code: Int) {
        setLoadMoreItemStatus(LoadMoreStatus.code(code))
    }

    private fun setLoadMoreItemStatus(newLoadMoreStatus: LoadMoreStatus) {
        bind(newLoadMoreStatus)
    }

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


    fun getItem(position: Int): Any? {
        return if (hasLoadMoreItem() && position == items.size) mDelegate.loadMoreItem else items[position]
    }

    override fun getItemCount() = super.getItemCount() + if (hasLoadMoreItem()) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (hasLoadMoreItem() && position == itemCount - 1)
            LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE
        else super.getItemViewType(position)
    }

    private fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        val itemType = holder.itemViewType
        return if (itemType == LoadMoreAdapterDelegate.ITEM_TYPE_LOAD_MORE)
            mDelegate.loadMoreItemViewBinder as ItemViewBinder<Any, RecyclerView.ViewHolder>
        else types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = getItem(position) ?: throw NullPointerException()
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }


}