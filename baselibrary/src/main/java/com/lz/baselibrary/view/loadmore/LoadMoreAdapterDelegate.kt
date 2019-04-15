package com.lz.baselibrary.view.loadmore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * LoadMoreAdapterDelegate
 * @author linzheng
 */
interface LoadMoreAdapterDelegate{

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