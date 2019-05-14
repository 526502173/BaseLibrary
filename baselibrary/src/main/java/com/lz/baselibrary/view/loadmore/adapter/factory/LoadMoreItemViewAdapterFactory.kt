package com.lz.baselibrary.view.loadmore.adapter.factory

import android.content.Context
import com.lz.baselibrary.view.loadmore.RetryListener
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreItemViewAdapter

/**
 * LoadMoreItemViewAdapterFactory
 * @author linzheng
 */
interface LoadMoreItemViewAdapterFactory {

    /**
     * 创建 LoadMoreAdapter 对象，
     * [context] 必须是 Activity 类型
     */
    fun create(context: Context, retryListener: RetryListener?): LoadMoreItemViewAdapter

}