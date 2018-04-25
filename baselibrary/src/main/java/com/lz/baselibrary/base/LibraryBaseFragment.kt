package com.lz.baselibrary.base

import android.content.Context
import android.view.View
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/08
 * desc   : LibraryBaseFragment
 * version: 1.0
</pre> *
 */
open abstract class LibraryBaseFragment : RxFragment(), BaseView {

    /**
     *  Fragment 依附的 Activity
     */
    private lateinit var mBaseView: BaseView

    /**
     * 是否初始化
     */
    private var mIsInitial = false


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !mIsInitial) {
            mIsInitial = !mIsInitial
            loadData()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mBaseView = context as BaseView
    }

    override fun showLoadingDialog() {
        mBaseView.showLoadingDialog()
    }

    override fun showLoadingLayout() {
    }

    override fun hideLoadingDialog() {
        mBaseView.hideLoadingDialog()
    }

    override fun showEmptyDataLayout() {
    }

    override fun showErrorLayout() {
    }

    override fun showSuccessLayout() {

    }

    /**
     * 加载数据
     */
    open abstract fun loadData()


    override fun reload(v: View?) {
    }

}
