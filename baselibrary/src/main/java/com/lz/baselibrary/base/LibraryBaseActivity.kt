package com.lz.baselibrary.base

import android.os.Bundle
import android.view.View
import com.lz.baselibrary.view.LoadingDialog
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity


/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/08
 * desc   : LibraryBaseActivity
 * version: 1.0
</pre> *
 */
open class LibraryBaseActivity : RxAppCompatActivity(), BaseView {

    private val mDialog by lazy(LazyThreadSafetyMode.NONE) {
        LoadingDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
