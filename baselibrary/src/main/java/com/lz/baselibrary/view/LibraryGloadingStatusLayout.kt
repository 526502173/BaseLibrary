package com.lz.baselibrary.view

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.billy.android.loading.Gloading
import com.lz.baselibrary.setVisibility

/**
 * @author linzheng
 */
abstract class LibraryGloadingStatusLayout(context: Context, val retry: Runnable) : ConstraintLayout(context) {


    init {
        LayoutInflater.from(context).inflate(getContentViewResId(), this,true)
    }


    @LayoutRes
    abstract fun getContentViewResId(): Int

    open fun setStatus(status: Int) {
        setVisibility(status != Gloading.STATUS_LOAD_SUCCESS)
    }

    companion object {
        //网络异常
        const val GLOADING_STATUS_NETWORK_ERROR = 5
        //Http code 不是 2xx
        const val GLOADING_STATUS_HTTP_ERROR = 6
        //接口异常
        const val GLOADING_STATUS_INTERFACE_ERROR = 7
        //其他异常
        const val GLOADING_STATUS_ORDER_ERROR = 10
    }


}