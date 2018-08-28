package com.lz.baselibrary.multitype

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lz.baselibrary.model.LOAD_MORE_STATUS_LOAD_COMPLETE
import com.lz.baselibrary.model.LOAD_MORE_STATUS_NOT_MORE
import com.lz.baselibrary.model.LoadMore
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register

/**
 *
 * 在添加数据的时候需要在 Items 的末尾加一个 LoadMore 的 Item
 * @author linzheng
 */
class LoadMoreAdapterWrapper(
        loadMoreListener: LoadMoreListener,
        private val multiTypeAdapter: MultiTypeAdapter,
        private val items: Items
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = multiTypeAdapter.onCreateViewHolder(parent, viewType)

    override fun getItemCount() = multiTypeAdapter.itemCount

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = multiTypeAdapter.bindViewHolder(holder, position)

    init {
        multiTypeAdapter.register(LoadMore::class, LoadMoreItemViewBinder(loadMoreListener))
    }

    fun loadMoreComplete() {
        getLoadMoreItem().status = LOAD_MORE_STATUS_LOAD_COMPLETE
        Thread.sleep(100)
        multiTypeAdapter.notifyItemChanged(getLoadMoreItemIndex())
    }

    fun notData() {
        getLoadMoreItem().status = LOAD_MORE_STATUS_NOT_MORE
        multiTypeAdapter.notifyItemChanged(getLoadMoreItemIndex())
    }

    private fun getLoadMoreItem() = items[getLoadMoreItemIndex()] as LoadMore

    private fun getLoadMoreItemIndex() = items.size - 1


}