package com.lz.baselibrary.base

import androidx.appcompat.app.AppCompatActivity
import com.billy.android.loading.Gloading
import com.lz.baselibrary.base.delegate.BaseViewDelegate
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView {

    /**
     * 用于给 RxJava 绑定 Activity 的生命周期
     */
    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this)
    }

    /**
     * BaseView 的委托
     */
    protected val baseViewDelegate: BaseViewDelegate by lazy {
        val delegate = BaseViewDelegate()
        delegate.holder = Gloading.getDefault().wrap(this).withRetry { retry() }
        delegate
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
