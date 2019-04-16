package com.lz.baselibrary.view.loadmore.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.loadmore.LoadMoreItemViewBinder

/**
 * LoadMoreAdapterDelegate
 * @author linzheng
 */
interface LoadMoreAdapterDelegate {

    val mLoadMoreItem: LoadMoreItem

    val mLoadMoreItemViewBinder: LoadMoreItemViewBinder

    fun getItemViewType(position: Int): Int

    fun onCreateViewHolder(parent: ViewGroup, indexViewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>)

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean

    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder)

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder)

    fun onViewRecycled(holder: RecyclerView.ViewHolder)

    fun getItemCount(): Int

    companion object {
        const val ITEM_TYPE_LOAD_MORE = 233331
    }

}