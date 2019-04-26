package com.lz.baselibrary.view.loadmore.adapter.factory

import android.content.Context
import com.lz.baselibrary.view.loadmore.adapter.LoadMoreAdapter

/**
 * @author linzheng
 */
interface LoadMoreAdapterFactory{

    /**
     * 创建 LoadMoreAdapter 对象，
     * [context] 必须是 Activity 类型
     */
    fun create(context: Context,retry: () -> Unit): LoadMoreAdapter

}