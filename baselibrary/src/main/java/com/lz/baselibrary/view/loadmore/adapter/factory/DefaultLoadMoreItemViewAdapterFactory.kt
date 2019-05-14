package com.lz.baselibrary.view.loadmore.adapter.factory

import android.content.Context
import com.lz.baselibrary.view.loadmore.RetryListener
import com.lz.baselibrary.view.loadmore.adapter.DefaultLoadMoreItemViewAdapter

/**
 * DefaultLoadMoreItemViewAdapterFactory
 * @author linzheng
 */
class DefaultLoadMoreItemViewAdapterFactory : LoadMoreItemViewAdapterFactory {
    override fun create(context: Context, retryListener: RetryListener?) =
            DefaultLoadMoreItemViewAdapter(context,retryListener)
}