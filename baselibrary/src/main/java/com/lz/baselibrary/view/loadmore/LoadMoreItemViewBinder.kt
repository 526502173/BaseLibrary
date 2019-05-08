package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.UIStatus
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreAdapter
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import me.drakeet.multitype.ItemViewBinder

/**
 * LoadMoreItemViewBinder
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val mFactory: LoadMoreAdapterFactory,
        private val mListener: LoadMoreListener,
        private val mRetry: () -> Unit
) : ItemViewBinder<LoadMoreStatus, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreStatus) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(mFactory.create(parent.context, mRetry), mListener)

    /*
     * LoadMoreViewHolder
     */
    class LoadMoreViewHolder(
            private val mAdapter: LoadMoreAdapter,
            private val mLoadMoreListener: LoadMoreListener
    ) : BaseViewHolder<LoadMoreStatus>(mAdapter.mItemView) {

        override fun bind(item: LoadMoreStatus) {
            when (item) {
                LoadMoreStatus.LOAD_MORE_NO_MORE -> mAdapter.noMore()
                LoadMoreStatus.LOAD_MORE_DISABLE -> mAdapter.disable()
                LoadMoreStatus.LOAD_MORE_READY -> {
                    //todo 这里的 onLoadMore() 没有调用
                    mLoadMoreListener.onLoadMore(itemView)
                    item.status = UIStatus.LOAD_MORE_LOADING
                    bind(item)
                }
                LoadMoreStatus.LOAD_MORE_LOADING -> {
                    mAdapter.loading()
                }
                else -> mAdapter.fail(item.failCode)
            }
        }
    }

    companion object {
        var sIsLoading = false
    }

}