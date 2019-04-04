package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreItem
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreAdapter
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import me.drakeet.multitype.ItemViewBinder
import timber.log.Timber

/**
 * LoadMoreItemViewBinder
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val mAdapterFactory: LoadMoreAdapterFactory,
        private val mListener: LoadMoreListener
) : ItemViewBinder<LoadMoreItem, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem) = holder.bind(item)

    //todo 这里会再调用调用 noMore 方法的时候调用一次，可能是缓存没有命中
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(mAdapterFactory.create(parent.context), mListener).also {
                Timber.d("LoadMoreItemViewBinder onCreateViewHolder()")
            }

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