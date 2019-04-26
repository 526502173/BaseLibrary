package com.lz.baselibrary.base.delegate

import com.lz.baselibrary.base.ListView
import com.lz.baselibrary.view.refresh.Refresh

/**
 * ListView 的委托
 * @author linzheng
 */
class ListViewDelegate(
        private val baseViewDelegate: BaseViewDelegate
) : ListView {

    lateinit var refresh: Refresh

    override fun refreshComplete() {
        if (this::refresh.isInitialized) refresh.complete()
    }

    override fun refreshing() {
        if (this::refresh.isInitialized) refresh.refreshing()
    }

    override fun showLoading() {
        baseViewDelegate.showLoading()
    }

    override fun showSuccess() {
        baseViewDelegate.showSuccess()
    }

    override fun showLoadFailed(status: Int) {
        baseViewDelegate.showLoadFailed(status)
    }

    override fun showEmpty() {
        baseViewDelegate.showEmpty()
    }

    override fun retry() {
        baseViewDelegate.retry()
    }
}