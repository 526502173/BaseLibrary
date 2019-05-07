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
        private val mFactory: LoadMoreAdapterFactory,
        private val mListener: LoadMoreListener,
        private val mRetry: () -> Unit
) : ItemViewBinder<LoadMoreItem, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreItem) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(mFactory.create(parent.context, mRetry), mListener)

    /*
     * LoadMoreViewHolder
     */
    class LoadMoreViewHolder(
            private val mAdapter: LoadMoreAdapter,
            private val mLoadMoreListener: LoadMoreListener
    ) : BaseViewHolder<LoadMoreItem>(mAdapter.mItemView) {

        override fun bind(item: LoadMoreItem) {
            Timber.d("LoadMoreItemViewBinder => bind() $item")
            when (item.status) {
                LoadMoreItem.LOAD_MORE_STATUS_NO_MORE -> mAdapter.noMore()
                LoadMoreItem.LOAD_MORE_STATUS_NORMAL -> mAdapter.normal()
                LoadMoreItem.LOAD_MORE_STATUS_FAIL -> mAdapter.fail(item.failCode)
                LoadMoreItem.LOAD_MORE_STATUS_LOADING -> {
                    mAdapter.loading()
                    mLoadMoreListener.onLoadMore(itemView)
                    item.status = LoadMoreItem.LOAD_MORE_STATUS_NORMAL
                }
            }
        }
    }


}