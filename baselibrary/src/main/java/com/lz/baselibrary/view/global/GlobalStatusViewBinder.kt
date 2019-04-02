package com.lz.baselibrary.view.global

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