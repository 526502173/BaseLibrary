package com.lz.baselibrary.base

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView {

    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy(LazyThreadSafetyMode.NONE) {
        AndroidLifecycleScopeProvider.from(this)
    }

    override fun showLoadingDialog() {
    }

    override fun showLoadingLayout() {
    }

    override fun hideLoadingDialog() {
    }

    override fun showEmptyDataLayout() {
    }

    override fun showErrorLayout() {
    }

    override fun showSuccessLayout() {
    }

    override fun reload(v: View?) {
    }

}
