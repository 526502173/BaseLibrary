package com.lz.baselibrary.view.adapter.factory

import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import com.lz.baselibrary.view.loadmore.RetryListener
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
data class AdapterConfig(
        val wrapperAdapter: MultiTypeAdapter,
        val isLoadMore: Boolean = false,
        val isDiff: Boolean = false,
        val isPagedList: Boolean = false,
        val loadMoreListener: LoadMoreListener? = null,
        val retryListener: RetryListener? = null
)