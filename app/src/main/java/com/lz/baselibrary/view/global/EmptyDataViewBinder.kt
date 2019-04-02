package com.lz.baselibrary.view.global

import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_global_loading_status.view.*

/**
 * @author linzheng
 */
class EmptyDataViewBinder : CommonViewBinder() {

    override fun bindView(layout: ViewGroup, retry: Runnable) {
        super.bindView(layout, retry)
        layout.tv_text.text = "空空如也..."
    }

}