package com.lz.baselibrary.initializer

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.billy.android.loading.Gloading
import com.lz.baselibrary.R
import com.lz.baselibrary.setVisibility
import com.lz.baselibrary.utils.initializer.SimpleInitialize

/**
 * Gloading 初始化
 * @author linzheng
 */
class GloadingInitialize : SimpleInitialize() {

    override fun initial(context: Context) {
        Gloading.initDefault { holder, convertView, status ->
            var layout: GlobalLoadingStatusLayout? = null
            if(convertView != null && convertView is GlobalLoadingStatusLayout){
                layout = convertView
            }
            if(layout == null){
                layout = GlobalLoadingStatusLayout(holder.context,holder.retryTask)
            }
            layout.setStatus(status)
             layout
        }
    }

    class GlobalLoadingStatusLayout(context: Context, val retry: Runnable?) : LinearLayout(context) {

        init {
            orientation = VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            LayoutInflater.from(context).inflate(R.layout.view_global_loading_status, this, true)
        }

        fun setStatus(status: Int) {
            setVisibility(status != Gloading.STATUS_LOAD_SUCCESS)
        }

    }

}