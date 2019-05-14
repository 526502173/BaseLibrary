package com.lz.baselibrary.view.loadmore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreItemViewAdapter
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.ItemViewBinder

/**
 * LoadMoreItemViewBinder
 * @author linzheng
 */
class LoadMoreItemViewBinder(
        private val delegate: LoadMoreAdapterDelegate
) : ItemViewBinder<LoadMoreStatus, LoadMoreItemViewBinder.LoadMoreViewHolder>() {

    override fun onBindViewHolder(holder: LoadMoreViewHolder, item: LoadMoreStatus) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): LoadMoreViewHolder =
            LoadMoreViewHolder(
                    LibraryLoadMoreInitialize.sLoadMoreAdapterFactory.create(
                            parent.context,
                            delegate.retryListener
                    ),
                    delegate
            )

    /*
     * LoadMoreViewHolder
     */
    class LoadMoreViewHolder(
            private val adapter: LoadMoreItemViewAdapter,
            private val delegate: LoadMoreAdapterDelegate
    ) : BaseViewHolder<LoadMoreStatus>(adapter.mItemView) {

        override fun bind(item: LoadMoreStatus) {
            when (item) {
                LoadMoreStatus.LOAD_MORE_NO_MORE -> adapter.noMore()
                LoadMoreStatus.LOAD_MORE_DISABLE -> adapter.disable()
                LoadMoreStatus.LOAD_MORE_LOADING -> adapter.loading()
                LoadMoreStatus.LOAD_MORE_READY -> {
                    delegate.loadMoreStatus = LoadMoreStatus.LOAD_MORE_LOADING
                    bind(LoadMoreStatus.LOAD_MORE_LOADING)
                    delegate.loadMoreListener?.onLoadMore(itemView)
                }
                else -> adapter.fail(item.failCode)
            }
        }
    }
}