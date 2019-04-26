package com.lz.baselibrary.base

import androidx.appcompat.app.AppCompatActivity
import com.billy.android.loading.Gloading
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView {

    /**
     * 用于给 RxJava 绑定 Activity 的生命周期
     */
    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this)
    }

    /**
     * BaseView 的委托
     */
    private val mDelegate: BaseViewDelegate by lazy {
        val delegate = BaseViewDelegate()
        delegate.holder = Gloading.getDefault().wrap(this).withRetry { retry() }
        delegate
    }

    override fun showLoading() {
        mDelegate.showLoading()
    }

    override fun showSuccess() {
        mDelegate.showSuccess()
    }

    override fun showLoadFailed(status: Int) {
        mDelegate.showLoadFailed(status)
    }

    override fun showEmpty() {
        mDelegate.showEmpty()
    }

    override fun retry() {
        showLoading()
    }

}
