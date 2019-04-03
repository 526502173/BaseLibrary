package com.lz.baselibrary.utils

import android.view.View
import android.view.ViewGroup

/**
 * RecyclerViewUtils
 * @author linzheng
 */
object RecyclerViewUtils {

    /**
     * 回收 Layout 的子 View
     * 试用场景为 RecyclerView 的 Item 中
     * 在 Item 中需要显示一个列表数据，数据类型要一致
     */
    fun recyclerLayout(layout: ViewGroup, count: Int, createView: () -> View) {
        val childCount = layout.childCount
        if (childCount == 0) return
        val diff = childCount - count
        if (diff > 0) {
            //多则减
            layout.removeViews(0, diff)
        } else {
            //少则加
            repeat(diff) { layout.addView(createView()) }
        }
    }

}