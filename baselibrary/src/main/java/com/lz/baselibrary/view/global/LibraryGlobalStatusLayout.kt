package com.lz.baselibrary.view.global

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.contains
import com.billy.android.loading.Gloading

/**
 * 全局状态管理 Layout，用于根据有不同的状态
 * 显示不同的界面，如：无网络，无数据，接口异常等等...
 * @author linzheng
 */
abstract class LibraryGlobalStatusLayout(context: Context, val retry: Runnable) : ConstraintLayout(context) {

    /**
     * key status value [GlobalStatusViewBinder]
     */
    protected val mStatus2ViewBinderArray = SparseArray<GlobalStatusViewBinder>(10)

    init {
        LayoutInflater.from(context).inflate(getContentViewResId(), this, true)
        mStatus2ViewBinderArray.put(Gloading.STATUS_LOAD_SUCCESS, LoadSuccessViewBinder())
    }

    /**
     * 根据 status 调用不同的 StatusViewBinder 的 bindView() 方法
     */
    fun setStatus(status: Int) {
        if (mStatus2ViewBinderArray.contains(status)) {
            resetAllView()
            mStatus2ViewBinderArray[status].bindView(this, retry)
        }
    }

    /**
     * 重置所有 View
     */
    open fun resetAllView() {
        visibility = View.VISIBLE
    }

    /**
     * 获取内容的 ResourceId
     */
    @LayoutRes
    abstract fun getContentViewResId(): Int

    companion object {
        //网络异常
        const val GLOADING_STATUS_NETWORK_ERROR = 5
        //Http code 不是 2xx
        const val GLOADING_STATUS_HTTP_ERROR = 6
        //接口异常
        const val GLOADING_STATUS_INTERFACE_ERROR = 7
    }
}