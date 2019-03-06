package com.lz.baselibrary.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


/**
 * @author linzheng
 */
open class LibraryBaseActivity : AppCompatActivity(), BaseView {

    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy {
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
