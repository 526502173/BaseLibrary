package com.lz.baselibrary.view

import android.content.Context
import android.graphics.Color
import android.view.View
import com.billy.android.loading.Gloading
import com.lz.baselibrary.R
import com.lz.baselibrary.setVisibility
import com.lz.baselibrary.view.global.*
import kotlinx.android.synthetic.main.view_global_loading_status.view.*

/**
 * @author linzheng
 */
class GlobalStatusLayout(context: Context, retry: Runnable) : LibraryGlobalStatusLayout(context, retry) {

    override fun getContentViewResId() = R.layout.view_global_loading_status

    init {
        //用于拦截事件，防止事件传递给底层 View
        setBackgroundColor(Color.WHITE)
        isClickable = true
        mStatus2ViewBinderArray.put(LibraryGlobalStatusLayout.GLOADING_STATUS_NETWORK_ERROR, NetworkErrorViewBinder())
        mStatus2ViewBinderArray.put(LibraryGlobalStatusLayout.GLOADING_STATUS_INTERFACE_ERROR, InterfaceErrorViewBinder())
        mStatus2ViewBinderArray.put(LibraryGlobalStatusLayout.GLOADING_STATUS_HTTP_ERROR, HttpErrorViewBinder())
        mStatus2ViewBinderArray.put(Gloading.STATUS_EMPTY_DATA, EmptyDataViewBinder())
        mStatus2ViewBinderArray.put(Gloading.STATUS_LOADING, LoadingViewBinder())
        mStatus2ViewBinderArray.put(Gloading.STATUS_LOAD_FAILED, LoadFailedViewBinder())
    }

    override fun resetAllView() {
        super.resetAllView()
        iv_icon.visibility = View.GONE
        pb_loading.visibility = View.GONE
        btn_retry.visibility = View.GONE
    }

}