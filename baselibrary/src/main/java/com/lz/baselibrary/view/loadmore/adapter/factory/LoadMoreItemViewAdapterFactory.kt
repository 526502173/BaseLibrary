package com.lz.baselibrary.view.loadmore.adapter.factory

import android.content.Context
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreItemViewAdapter

/**
 * @author linzheng
 */
interface LoadMoreItemViewAdapterFactory{

    /**
     * 创建 LoadMoreAdapter 对象，
     * [context] 必须是 Activity 类型
     */
    fun create(context: Context,retry: () -> Unit): LoadMoreItemViewAdapter

}