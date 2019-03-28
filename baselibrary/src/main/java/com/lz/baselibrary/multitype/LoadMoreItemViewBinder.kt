package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreView
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class LoadMoreItemViewBinder(private val mLoadMoreListener: LoadMoreListener) : ItemViewBinder<LoadMoreItem, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(LoadMoreView.create(parent.context), mLoadMoreListener)

    class LoadMoreViewHolder(itemView: View, private val mLoadMoreListener: LoadMoreListener) : BaseViewHolder<LoadMoreItem>(itemView) {
        override fun bind(item: LoadMoreItem) {
            val loadMoreView = itemView as LoadMoreView
            when (item.status) {
                LoadMoreItem.LOAD_MORE_STATUS_NO_MORE -> {
                    loadMoreView.noMore()
                }
                LoadMoreItem.LOAD_MORE_STATUS_LOADING -> {

                }
                LoadMoreItem.LOAD_MORE_STATUS_NORMAL -> {
                    item.status = LoadMoreItem.LOAD_MORE_STATUS_LOADING
                    loadMoreView.loading()
                    mLoadMoreListener.loadMore(itemView)
                }
            }
        }
    }


}