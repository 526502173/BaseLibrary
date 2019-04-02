package com.lz.baselibrary.view.global

import android.view.View
import android.view.ViewGroup

/**
 * 加载成功 ViewBinder
 * @author linzheng
 */
class LoadSuccessViewBinder : GlobalStatusViewBinder {
    override fun bindView(layout: ViewGroup, retry: Runnable) {
        layout.visibility = View.GONE
    }
}