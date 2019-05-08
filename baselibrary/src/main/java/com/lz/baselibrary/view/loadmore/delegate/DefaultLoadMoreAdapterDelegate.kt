package com.lz.baselibrary.view.loadmore.delegate

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreItemViewBinder
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import timber.log.Timber

/**
 * DefaultLoadMoreAdapterDelegate
 * @author linzheng
 */
//todo 优化参数
class DefaultLoadMoreAdapterDelegate private constructor(
        private val adapter: MultiTypeAdapter,
        private val loadMoreAdapterFactory: LoadMoreAdapterFactory,
        private val listener: LoadMoreListener,
        private val getDataItem: (position: Int) -> Any?,
        private val getDataItemCount: () -> Int,
        private val retry: () -> Unit
) : LoadMoreAdapterDelegate {

    init {
        adapter.register(LoadMoreStatus::class, LoadMoreItemViewBinder(loadMoreAdapterFactory, listener, retry))
    }

    override fun getItemCount() = getDataItemCount() + if (hasLoadMoreItem()) 1 else 0

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
        val dataItemCount = getDataItemCount()
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

    override val loadMoreItemViewBinder: LoadMoreItemViewBinder by lazy {
        LoadMoreItemViewBinder(loadMoreAdapterFactory, listener, retry)
    }

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
        else getDataItem(position)
    }

    override fun isLoadMoreItemPosition(position: Int) = hasLoadMoreItem() && position == getDataItemCount()

    companion object {
        fun create(
                adapter: MultiTypeAdapter,
                loadMoreAdapterFactory: LoadMoreAdapterFactory,
                listener: LoadMoreListener,
                getItem: (position: Int) -> Any?,
                getItemCount: () -> Int,
                retry: () -> Unit
        ): LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate(
                adapter,
                loadMoreAdapterFactory,
                listener,
                getItem,
                getItemCount,
                retry
        )
    }

}