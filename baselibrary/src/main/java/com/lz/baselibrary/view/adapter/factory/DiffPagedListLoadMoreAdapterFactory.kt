package com.lz.baselibrary.view.adapter.factory

import com.lz.baselibrary.view.adapter.diff.PagedListDiffer
import com.lz.baselibrary.view.adapter.loadmore.DiffPagedListLoadMoreAdapter
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate

/**
 * @author linzheng
 */
class DiffPagedListLoadMoreAdapterFactory : AbstractAdapterFactory<DiffPagedListLoadMoreAdapter>() {

    override fun createAdapter(config: AdapterConfig): DiffPagedListLoadMoreAdapter {
        val delegate = DefaultLoadMoreAdapterDelegate()
        val differ = PagedListDiffer()
        val adapter = DiffPagedListLoadMoreAdapter(config.wrapperAdapter, delegate, differ)
        differ.adapter = adapter
        delegate.adapter = adapter
        delegate.loadMoreListener = config.loadMoreListener
        delegate.retryListener = config.retryListener
        return adapter
    }
}