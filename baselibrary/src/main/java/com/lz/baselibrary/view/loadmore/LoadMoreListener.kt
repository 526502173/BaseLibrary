package com.lz.baselibrary.view.itemdecoration.loadmore

import android.view.View

/**
 * 上拉加载的的监听器
 * @author linzheng
 */
//todo 用 Kotlin 的高阶函数替代
interface LoadMoreListener {
    fun onLoadMore(view: View)
}