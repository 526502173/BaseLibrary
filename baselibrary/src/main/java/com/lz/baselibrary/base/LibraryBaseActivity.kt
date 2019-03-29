package com.lz.baselibrary.base

import androidx.appcompat.app.AppCompatActivity
import com.billy.android.loading.Gloading
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView {

    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this)
    }

    protected val mHolder: Gloading.Holder by lazy {
        Gloading.getDefault().wrap(this)
    }

    override fun showLoading() {
        mHolder.showLoading()
    }

    override fun showSuccess() {
        mHolder.showLoadSuccess()
    }

    override fun showLoadFailed() {
        mHolder.showLoadFailed()
    }

    override fun showEmpty() {
        mHolder.showEmpty()
    }

}
