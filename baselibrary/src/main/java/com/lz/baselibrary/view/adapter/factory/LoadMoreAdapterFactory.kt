package com.lz.baselibrary.view.adapter.factory

import com.lz.baselibrary.view.adapter.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.loadmore.delegate.DefaultLoadMoreAdapterDelegate

/**
 * @author linzheng
 */
class LoadMoreAdapterFactory : AbstractAdapterFactory<LoadMoreAdapter>() {
    override fun createAdapter(config: AdapterConfig): LoadMoreAdapter {
        val delegate = DefaultLoadMoreAdapterDelegate()
        val adapter = LoadMoreAdapter(config.wrapperAdapter, delegate)
        delegate.adapter = adapter
        return adapter
    }
}