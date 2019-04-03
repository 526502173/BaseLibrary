package com.lz.baselibrary.view.globalstatus

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_global_loading_status.view.*

/**
 * @author linzheng
 */
class LoadingViewBinder : CommonViewBinder() {
    override fun bindView(layout: ViewGroup, retry: Runnable) {
        layout.pb_loading.visibility = View.VISIBLE
        layout.tv_text.text = "转啊转..."
    }

    override fun isNeedRetryButton() = false

}