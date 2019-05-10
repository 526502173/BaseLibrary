package com.lz.baselibrary.view.loadmore.delegate

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreItemViewBinder
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import timber.log.Timber

/**
 * DefaultLoadMoreAdapterDelegate
 * @author linzheng
 */
//todo 优化参数 1.1
class DefaultLoadMoreAdapterDelegate(
        listener: LoadMoreListener,
        retry: () -> Unit,
        private val adapter: MultiTypeAdapter,
        private val callback: LoadMoreDelegateCallback
        ) : LoadMoreAdapterDelegate {
    init {
        adapter.register(LoadMoreStatus::class, LoadMoreItemViewBinder(listener, this, retry))
    }

    override fun getItemCount() = callback.getDataItemCount() + if (hasLoadMoreItem()) 1 else 0

    override fun getItemId(position: Int): Long {
        val itemViewType = getItemViewType(position)
        return adapter.types.getType<Any>(itemViewType).binder.getItemId(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int {
        return indexInTypesOf(position, getItem(position)!!)
    }

    private fun indexInTypesOf(position: Int, item: Any): Int {
        val index = adapter.types.firstIndexOf(item.javaClass)
        if (index != -1) {
            val linker = adapter.types.getType<Any>(index).linker
            return index + linker.index(position, item)
        }
        throw NullPointerException()
    }

    override fun hasLoadMoreItem() = loadMoreStatus != null && loadMoreStatus != LoadMoreStatus.LOAD_MORE_DISABLE

    override fun bindLoadMoreStatus(newStatus: LoadMoreStatus) {
        Timber.d("LoadMore => newStatus = $newStatus oldStatus = $loadMoreStatus")
        val previousStatus = loadMoreStatus
        val previousHasLoadMoreItem = hasLoadMoreItem()
        this.loadMoreStatus = newStatus
        val hasLoadMoreItem = hasLoadMoreItem()
        val dataItemCount = callback.getDataItemCount()
        if (previousHasLoadMoreItem != hasLoadMoreItem) {
            if (previousHasLoadMoreItem) {
                Timber.d("LoadMore => removed()")
                adapter.notifyItemRemoved(dataItemCount)
            } else {
                Timber.d("LoadMore => inserted()")
                adapter.notifyItemInserted(dataItemCount)
            }
        } else if (hasLoadMoreItem && previousStatus != newStatus) {
            Timber.d("LoadMore => changed()")
            adapter.notifyItemChanged(dataItemCount)
        }
    }

    override var loadMoreStatus: LoadMoreStatus? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = getItem(position) ?: throw NullPointerException()
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    override fun noMore() {
        bindLoadMoreStatus(LoadMoreStatus.LOAD_MORE_NO_MORE)
    }

    override fun loading() {
        bindLoadMoreStatus(LoadMoreStatus.LOAD_MORE_LOADING)
    }

    override fun disable() {
        bindLoadMoreStatus(LoadMoreStatus.LOAD_MORE_DISABLE)
    }

    override fun fail(code: Int) {
        bindLoadMoreStatus(LoadMoreStatus.code(code))
    }

    override fun readly() {
        bindLoadMoreStatus(LoadMoreStatus.LOAD_MORE_READY)
    }

    private fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        return adapter.types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    private fun getItem(position: Int): Any? {
        return if (isLoadMoreItemPosition(position)) loadMoreStatus
        else callback.getDataItem(position)
    }

    override fun isLoadMoreItemPosition(position: Int) = hasLoadMoreItem() && position == callback.getDataItemCount()

    companion object {
        fun create(
                adapter: MultiTypeAdapter,
                listener: LoadMoreListener,
                callback: LoadMoreDelegateCallback,
                retry: () -> Unit
        ): LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate(
                listener,
                retry,
                adapter,
                callback
        )
    }

}