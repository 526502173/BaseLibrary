package com.lz.baselibrary.base

import androidx.appcompat.app.AppCompatActivity
import com.billy.android.loading.Gloading
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView, Runnable {

    /**
     * 用于给 RxJava 绑定 Activity 的生命周期
     */
    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this)
    }

    protected val mHolder: Gloading.Holder by lazy {
        Gloading.getDefault().wrap(this).withRetry(this)
    }

    override fun showLoading() {
        mHolder.showLoading()
    }

    override fun showSuccess() {
        mHolder.showLoadSuccess()
    }

    override fun showLoadFailed(status: Int) {
        mHolder.showLoadingStatus(status)
    }

    override fun showEmpty() {
        mHolder.showEmpty()
    }

    override fun run() {

    }

}
