package com.lz.baselibrary.view.adapter.factory

import androidx.paging.PagedList
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.view.adapter.BaseAdapter
import com.lz.baselibrary.view.adapter.CommonDiffAdapter
import com.lz.baselibrary.view.adapter.DiffListAdapter
import com.lz.baselibrary.view.adapter.DiffPagedListAdapter
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.ListDiffer
import com.lz.baselibrary.view.adapter.diff.PagedListDiffer
import com.lz.baselibrary.view.adapter.loadmore.DiffListLoadMoreAdapter
import com.lz.baselibrary.view.adapter.loadmore.DiffPagedListLoadMoreAdapter
import com.lz.baselibrary.view.adapter.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.LoadMoreItemViewBinder
import com.lz.baselibrary.view.loadmore.RetryListener
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
//todo 使用工厂模式优化此类代码,因为设计上的问题，用工厂模式优化存在难点，但是也是有可能实现的。
object AdapterFactory {

    fun createDiffListAdapter(wrapperAdapter: MultiTypeAdapter): DiffListAdapter {
        return create(wrapperAdapter, isPagedList = false, isLoadMore = false)
    }

    fun createDiffPagedListAdapter(wrapperAdapter: MultiTypeAdapter): DiffPagedListAdapter {
        return create(wrapperAdapter, isPagedList = true, isLoadMore = false)
    }

    fun createDiffListLoadMoreAdapter(
            wrapperAdapter: MultiTypeAdapter,
            loadMoreListener: LoadMoreListener?,
            retryListener: RetryListener?
    ): DiffListLoadMoreAdapter {
        return create(wrapperAdapter, false, true, loadMoreListener, retryListener)
    }

    fun createDiffPagedLoadMoreAdapter(
            wrapperAdapter: MultiTypeAdapter,
            retryListener: RetryListener?
    ): DiffPagedListLoadMoreAdapter {
        return create(wrapperAdapter, isPagedList = true, isLoadMore = true, retryListener = retryListener)
    }

    fun createLoadMoreAdapter(
            wrapperAdapter: MultiTypeAdapter,
            loadMoreListener: LoadMoreListener?,
            retryListener: RetryListener?
    ): LoadMoreAdapter {
        return create(wrapperAdapter, false, true, loadMoreListener, retryListener)
    }

    fun <T> create(
            wrapperAdapter: MultiTypeAdapter,
            isPagedList: Boolean,
            isLoadMore: Boolean,
            loadMoreListener: LoadMoreListener? = null,
            retryListener: RetryListener? = null
    ): T {
        val differ = if (isPagedList) PagedListDiffer() else ListDiffer()
        val adapter: BaseAdapter = if (isLoadMore) {
            val delegate: LoadMoreAdapterDelegate = DefaultLoadMoreAdapterDelegate()
            delegate.loadMoreListener = loadMoreListener
            delegate.retryListener = retryListener
            val adapter = if (isPagedList) DiffPagedListLoadMoreAdapter(wrapperAdapter, delegate, differ as Differ<PagedList<Any>>)
            else DiffListLoadMoreAdapter(wrapperAdapter, delegate, differ as Differ<List<Any>>)
            adapter.register(LoadMoreStatus::class, LoadMoreItemViewBinder(delegate))
            delegate.adapter = adapter
            adapter
        } else CommonDiffAdapter(wrapperAdapter, differ)
        differ.adapter = adapter
        return adapter as T
    }


    fun <T> createFromFactory(config: AdapterConfig): T {
        val factory = when {
            config.isDiff && config.isPagedList && config.isLoadMore -> DiffPagedListLoadMoreAdapterFactory()
            config.isDiff && !config.isPagedList && config.isLoadMore -> DiffListLoadMoreAdapterFactory()
            config.isDiff && config.isPagedList && !config.isLoadMore -> DiffPagedListAdapterFactory()
            config.isDiff && !config.isPagedList && !config.isLoadMore -> DiffListAdapterFactory()
            !config.isDiff && !config.isPagedList && config.isLoadMore -> LoadMoreAdapterFactory()
            else -> throw IllegalArgumentException("不支持类型")
        }
        return factory.createAdapter(config) as T
    }

}