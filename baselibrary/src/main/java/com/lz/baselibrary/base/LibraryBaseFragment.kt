package com.lz.baselibrary.base

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View

/**
 * @author linzheng
 */
open abstract class LibraryBaseFragment : Fragment(), BaseView {

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
