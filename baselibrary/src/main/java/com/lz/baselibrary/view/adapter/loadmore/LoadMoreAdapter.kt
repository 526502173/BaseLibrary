package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.adapter.BaseAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreDelegateCallback
import me.drakeet.multitype.MultiTypeAdapter

/**
 * LoadMore + MultiType
 * @author linzheng
 */
class LoadMoreAdapter(
        private val wrapperAdapter: MultiTypeAdapter,
        private val delegate: LoadMoreAdapterDelegate
) : BaseAdapter(wrapperAdapter), LoadMore by delegate, LoadMoreDelegateCallback {

    override fun setLoadMoreListener(listener: LoadMoreListener) {
    }

    override var items: List<Any>
        get() = wrapperAdapter.items
        set(value) {
            wrapperAdapter.items = value
        }

    override fun getItemCount() = delegate.getItemCount()

    override fun getItemViewType(position: Int) = delegate.getItemViewType(position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        delegate.onBindViewHolder(holder, position, payloads)
    }

    override fun getDataItem(position: Int) = items[position]

    override fun getDataItemCount() = items.size


}