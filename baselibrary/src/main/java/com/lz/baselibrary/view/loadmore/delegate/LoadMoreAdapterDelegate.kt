package com.lz.baselibrary.view.loadmore.delegate

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore

/**
 * LoadMoreAdapterDelegate
 * @author linzheng
 */
interface LoadMoreAdapterDelegate: LoadMore {

    var loadMoreStatus: LoadMoreStatus?

    fun getItemViewType(position: Int): Int

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>)

    fun hasLoadMoreItem(): Boolean

    fun bindLoadMoreStatus(newStatus: LoadMoreStatus)

    fun getItemId(position: Int): Long

    fun isLoadMoreItemPosition(position: Int): Boolean

    fun getItemCount():Int
}