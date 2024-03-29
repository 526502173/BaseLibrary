package com.lz.baselibrary.view.loadmore.delegate

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.view.adapter.loadmore.CommonLoadMoreAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.RetryListener
import me.drakeet.multitype.ItemViewBinder
import timber.log.Timber

/**
 * DefaultLoadMoreAdapterDelegate
 *
 * 这个方法汇总的 getCount 之所以需要使用 Callback 接口回调的原因是
 * 如果直接在这里使用 adapter 的 getItemCount 就会出现下面的情况:
 *
 * adapter -> delegate
 * delegate -> adapter
 * ....如此反复
 * @author linzheng
 */
class DefaultLoadMoreAdapterDelegate : LoadMoreAdapterDelegate {

    override lateinit var adapter: CommonLoadMoreAdapter
    override var loadMoreListener: LoadMoreListener? = null
    override var retryListener: RetryListener? = null

    override fun getItemCount() = adapter.getDataItemCount() + if (hasLoadMoreItem()) 1 else 0

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
        if (!this::adapter.isInitialized) return
        Timber.d("LoadMore => newStatus = $newStatus oldStatus = $loadMoreStatus")
        val previousStatus = loadMoreStatus
        val previousHasLoadMoreItem = hasLoadMoreItem()
        this.loadMoreStatus = newStatus
        val hasLoadMoreItem = hasLoadMoreItem()
        val dataItemCount = adapter.getDataItemCount()
        if (previousHasLoadMoreItem != hasLoadMoreItem) {
            if (previousHasLoadMoreItem) {
                Timber.d("LoadMore => removed() dataItemCount = $dataItemCount")
                adapter.notifyItemRemoved(dataItemCount)
            } else {
                Timber.d("LoadMore => inserted()")
                adapter.notifyItemInserted(dataItemCount)
            }
        } else if (hasLoadMoreItem && previousStatus != newStatus) {
            Timber.d("LoadMore => changed()")
            adapter.notifyItemChanged(dataItemCount)
        }
        Timber.d("LoadMore => dataItemCount = $dataItemCount itemCount = ${getItemCount()}")
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

    override fun ready() {
        bindLoadMoreStatus(LoadMoreStatus.LOAD_MORE_READY)
    }

    /**
     * 此方法来之 MultiTypeAdapter 中，用于从 Types 中获取到对应的 ItemViewBinder 对象
     */
    private fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        return adapter.types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    private fun getItem(position: Int): Any? =
            if (this::adapter.isInitialized) {
                if (isLoadMoreItemPosition(position)) loadMoreStatus
                else adapter.getDataItem(position)
            } else throw IllegalArgumentException("adapter is not initialized!")

    override fun isLoadMoreItemPosition(position: Int) =
            if (this::adapter.isInitialized)
                hasLoadMoreItem() && position == adapter.getDataItemCount()
            else throw IllegalArgumentException("adapter is not initialized!")

}