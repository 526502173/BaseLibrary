package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.adapter.BaseAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreDelegateCallback
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class CommonLoadMoreAdapter(
        adapter: MultiTypeAdapter,
        private val delegate: LoadMoreAdapterDelegate
) : BaseAdapter(adapter), LoadMoreDelegateCallback {

    override fun getItemCount(): Int {
        return delegate.getItemCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        delegate.onBindViewHolder(holder,position,payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return delegate.getItemViewType(position)
    }

    //todo 实现这个方法
    fun setLoadMoreListener(listener: LoadMoreListener) {

    }
}