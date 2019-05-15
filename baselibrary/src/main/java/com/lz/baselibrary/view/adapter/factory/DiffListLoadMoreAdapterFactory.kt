package com.lz.baselibrary.view.adapter.factory

import com.lz.baselibrary.view.adapter.diff.ListDiffer
import com.lz.baselibrary.view.adapter.loadmore.DiffListLoadMoreAdapter
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate

/**
 * @author linzheng
 */
class DiffListLoadMoreAdapterFactory : AbstractAdapterFactory<DiffListLoadMoreAdapter>() {

    override fun createAdapter(config: AdapterConfig): DiffListLoadMoreAdapter {
        val delegate = DefaultLoadMoreAdapterDelegate()
        val differ = ListDiffer()
        val adapter = DiffListLoadMoreAdapter(config.wrapperAdapter, delegate, differ)
        differ.adapter = adapter
        delegate.adapter = adapter
        delegate.loadMoreListener = config.loadMoreListener
        delegate.retryListener = config.retryListener
        return adapter
    }
}