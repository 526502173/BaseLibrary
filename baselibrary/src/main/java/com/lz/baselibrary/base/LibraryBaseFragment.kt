package com.lz.baselibrary.base

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

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

    /**
     * AutoDispose
     */
    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy(LazyThreadSafetyMode.NONE) {
        AndroidLifecycleScopeProvider.from(this)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint && !mIsInitial) {
            mIsInitial = !mIsInitial
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoDisposable(mScopeProvider)
                    .subscribe {
                        loadData()
                    }
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
