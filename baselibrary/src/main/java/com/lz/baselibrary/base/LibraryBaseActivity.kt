package com.lz.baselibrary.base

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lz.baselibrary.view.LoadingDialog
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView {

    protected val mScopeProvider by lazy(LazyThreadSafetyMode.NONE) {
        AndroidLifecycleScopeProvider.from(this)
    }

    private val mDialog by lazy(LazyThreadSafetyMode.NONE) {
        LoadingDialog()
    }

    override fun showLoadingDialog() {
        mDialog.show(supportFragmentManager)
    }

    override fun showLoadingLayout() {
    }

    override fun hideLoadingDialog() {
        mDialog.dismiss()
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
