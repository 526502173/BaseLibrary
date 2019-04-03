package com.lz.baselibrary.view.globalstatus

import android.view.ViewGroup

/**
 * @author linzheng
 */
interface GlobalStatusViewBinder {

    /**
     * 设置 View
     */
    fun bindView(layout: ViewGroup, retry: Runnable)

}