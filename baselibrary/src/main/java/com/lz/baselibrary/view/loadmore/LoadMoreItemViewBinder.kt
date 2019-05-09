package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreAdapter
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.ItemViewBinder
import timber.log.Timber

/**
 * LoadMoreItemViewBinder
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val mFactory: LoadMoreAdapterFactory,
        private val mListener: LoadMoreListener,
        private val mLoadMoreAdapterDelegate: LoadMoreAdapterDelegate,
        private val mRetry: () -> Unit
) : ItemViewBinder<LoadMoreStatus, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreStatus) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(mFactory.create(parent.context, mRetry), mLoadMoreAdapterDelegate, mListener)

    /*
     * LoadMoreViewHolder
     */
    class LoadMoreViewHolder(
            private val mAdapter: LoadMoreAdapter,
            private val mLoadMoreAdapterDelegate: LoadMoreAdapterDelegate,
            private val mLoadMoreListener: LoadMoreListener
    ) : BaseViewHolder<LoadMoreStatus>(mAdapter.mItemView) {

        override fun bind(item: LoadMoreStatus) {
            Timber.d("currentStatus = $item")
            when (item) {
                LoadMoreStatus.LOAD_MORE_NO_MORE -> mAdapter.noMore()
                LoadMoreStatus.LOAD_MORE_DISABLE -> mAdapter.disable()
                LoadMoreStatus.LOAD_MORE_LOADING -> mAdapter.loading()
                LoadMoreStatus.LOAD_MORE_READY -> {
                    Timber.d("onLoadMore()")
                    mLoadMoreAdapterDelegate.loadMoreStatus = LoadMoreStatus.LOAD_MORE_LOADING
                    bind(LoadMoreStatus.LOAD_MORE_LOADING)
                    mLoadMoreListener.onLoadMore(itemView)
                }
                else -> mAdapter.fail(item.failCode)
            }
        }
    }
}