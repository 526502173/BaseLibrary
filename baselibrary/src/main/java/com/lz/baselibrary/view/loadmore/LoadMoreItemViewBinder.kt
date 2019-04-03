package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.ItemViewBinder

/**
 * LoadMoreItemViewBinder
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val mAdapter: LoadMoreAdapter,
        private val mListener: LoadMoreListener
) : ItemViewBinder<LoadMoreItem, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(mAdapter, mListener)

    /**
     * LoadMoreViewHolder
     */
    class LoadMoreViewHolder(
            private val mAdapter: LoadMoreAdapter,
            private val mLoadMoreListener: LoadMoreListener
    ) : BaseViewHolder<LoadMoreItem>(mAdapter.mItemView) {

        override fun bind(item: LoadMoreItem) {
            when (item.status) {
                LoadMoreItem.LOAD_MORE_STATUS_NO_MORE -> {
                    mAdapter.noMore()
                }
                LoadMoreItem.LOAD_MORE_STATUS_LOADING -> {
                    //nothing
                }
                LoadMoreItem.LOAD_MORE_STATUS_NORMAL -> {
                    item.status = LoadMoreItem.LOAD_MORE_STATUS_LOADING
                    mAdapter.loading()
                    mLoadMoreListener.loadMore(itemView)
                }
            }
        }
    }


}