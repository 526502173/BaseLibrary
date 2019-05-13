package com.lz.baselibrary.view.loadmore.adapter.factory

import android.content.Context
import com.lz.baselibrary.view.loadmore.adapter.DefaultLoadMoreItemViewAdapter

/**
 * SimpleLoadMoreAdapterFactory
 * @author linzheng
 */
class DefaultLoadMoreItemViewAdapterFactory : LoadMoreItemViewAdapterFactory {
    override fun create(context: Context, retry: () -> Unit) = DefaultLoadMoreItemViewAdapter(context,retry)
}