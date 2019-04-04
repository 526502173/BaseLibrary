package com.lz.baselibrary.view.loadmore.adapter.factory

import android.content.Context
import com.lz.baselibrary.view.loadmore.adapter.SimpleLoadMoreAdapter

/**
 * SimpleLoadMoreAdapterFactory
 * @author linzheng
 */
class SimpleLoadMoreAdapterFactory : LoadMoreAdapterFactory {
    override fun create(context: Context) = SimpleLoadMoreAdapter(context)
}