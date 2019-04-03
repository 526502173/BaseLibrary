package com.lz.baselibrary.view.globalstatus

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_global_loading_status.view.*

/**
 * @author linzheng
 */
class NetworkErrorViewBinder : CommonViewBinder(){
    override fun bindView(layout: ViewGroup, retry: Runnable) {
        super.bindView(layout, retry)
        layout.iv_icon.visibility = View.VISIBLE
        layout.tv_text.text = "没有网络~"
    }
}