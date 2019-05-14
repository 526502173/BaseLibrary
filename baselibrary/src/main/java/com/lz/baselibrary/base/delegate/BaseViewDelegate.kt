package com.lz.baselibrary.base.delegate

import android.view.View
import com.billy.android.loading.Gloading
import com.lz.baselibrary.base.BaseView
import com.lz.baselibrary.view.loadmore.RetryListener

/**
 * BaseView 的委托
 * @author linzheng
 */
class BaseViewDelegate: BaseView,RetryListener {

    lateinit var holder: Gloading.Holder

    override fun showLoading() {
        if (this::holder.isInitialized) holder.showLoading()
    }

    override fun showSuccess() {
        if (this::holder.isInitialized) holder.showLoadSuccess()
    }

    override fun showLoadFailed(status: Int) {
        if (this::holder.isInitialized) holder.showLoadingStatus(status)
    }

    override fun showEmpty() {
        if (this::holder.isInitialized) holder.showEmpty()
    }

    override fun onRetry() {
        showLoading()
    }

}