package com.lz.baselibrary.view.global

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_global_loading_status.view.*

/**
 * @author linzheng
 */
abstract class CommonViewBinder : GlobalStatusViewBinder {

    override fun bindView(layout: ViewGroup, retry: Runnable) {
        if (isNeedRetryButton()) {
            layout.btn_retry.visibility = View.VISIBLE
            layout.btn_retry.setOnClickListener { retry.run() }
        }
    }

    /**
     * 是否需要重试按钮
     */
    open fun isNeedRetryButton() = true

}