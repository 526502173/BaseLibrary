package com.lz.baselibrary.view

import android.content.Context
import com.billy.android.loading.Gloading
import com.lz.baselibrary.R
import com.lz.baselibrary.setVisibility
import kotlinx.android.synthetic.main.view_global_loading_status.view.*

/**
 * @author linzheng
 */
class GloadingStatusLayout(context: Context, retry: Runnable) : LibraryGloadingStatusLayout(context, retry) {

    override fun getContentViewResId() = R.layout.view_global_loading_status

    init {
        isClickable = true
    }

    override fun setStatus(status: Int) {
        resetAllView()
        var isNeedRetry = true
        when (status) {
            LibraryGloadingStatusLayout.GLOADING_STATUS_NETWORK_ERROR -> {
                iv_icon.setVisibility(true)
                iv_icon.setImageResource(R.drawable.no_network)
                tv_text.text = "网络么得了..."
            }
            LibraryGloadingStatusLayout.GLOADING_STATUS_HTTP_ERROR -> {
                tv_text.text = "服务器出问题了..."
            }
            LibraryGloadingStatusLayout.GLOADING_STATUS_INTERFACE_ERROR -> {
                tv_text.text = "接口出问题了..."
            }
            Gloading.STATUS_LOADING -> {
                pb_loading.setVisibility(true)
                tv_text.text = "爱的魔力转圈圈..."
                isNeedRetry = false
            }
            Gloading.STATUS_LOAD_FAILED -> {
                tv_text.text = "加载失败..."
            }
            Gloading.STATUS_EMPTY_DATA -> {
                tv_text.text = "空空如也..."
            }
        }
        if (isNeedRetry) {
            btn_retry.setVisibility(true)
            btn_retry.setOnClickListener { retry.run() }
        }
        super.setStatus(status)
    }

    private fun resetAllView() {
        iv_icon.setVisibility(false)
        pb_loading.setVisibility(false)
        btn_retry.setVisibility(false)
    }

}