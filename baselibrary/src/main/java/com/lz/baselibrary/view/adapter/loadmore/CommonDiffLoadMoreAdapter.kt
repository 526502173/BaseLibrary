package com.lz.baselibrary.view.adapter.loadmore

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.adapter.CommonDiffAdapter
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class CommonDiffLoadMoreAdapter<T>(
        wrapperAdapter: MultiTypeAdapter,
        private val delegate: LoadMoreAdapterDelegate,
        differ: Differ<T>
) : CommonDiffAdapter<T>(wrapperAdapter, differ), LoadMore by delegate {

    override fun getItemCount(): Int {
        return delegate.getItemCount()
    }

    override fun getItemViewType(position: Int) = delegate.getItemViewType(position)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        delegate.onBindViewHolder(holder, position, payloads)
    }
}