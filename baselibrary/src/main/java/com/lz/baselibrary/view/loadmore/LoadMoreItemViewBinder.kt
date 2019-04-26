package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreAdapter
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import me.drakeet.multitype.ItemViewBinder

/**
 * LoadMoreItemViewBinder
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val adapterFactory: LoadMoreAdapterFactory,
        private val listener: LoadMoreListener,
        private val retry: () -> Unit
) : ItemViewBinder<LoadMoreItem, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(adapterFactory.create(parent.context, retry), listener)

    /*
     * LoadMoreViewHolder
     */
    class LoadMoreViewHolder(
            private val mAdapter: LoadMoreAdapter,
            private val mLoadMoreListener: LoadMoreListener
    ) : BaseViewHolder<LoadMoreItem>(mAdapter.mItemView) {

        override fun bind(item: LoadMoreItem) {
            when (item.status) {
                LoadMoreItem.LOAD_MORE_STATUS_NO_MORE -> mAdapter.noMore()
                LoadMoreItem.LOAD_MORE_STATUS_LOADING -> mAdapter.loading()
                LoadMoreItem.LOAD_MORE_STATUS_NORMAL -> {
                    mAdapter.normal()
                    mLoadMoreListener.onLoadMore(itemView)
                }
                LoadMoreItem.LOAD_MORE_STATUS_FAIL -> {
                    mAdapter.fail(item.failCode)
                }
            }
        }
    }


}