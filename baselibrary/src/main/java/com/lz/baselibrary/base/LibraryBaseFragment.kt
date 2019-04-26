package com.lz.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.billy.android.loading.Gloading
import com.lz.baselibrary.base.delegate.BaseViewDelegate
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * LibraryBaseFragment
 * @author linzheng
 */
open abstract class LibraryBaseFragment : Fragment(), BaseView {

    /**
     * AutoDispose
     */
    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy(LazyThreadSafetyMode.NONE) {
        AndroidLifecycleScopeProvider.from(this)
    }

    /**
     * Gloading Holder 对象
     */
    private lateinit var mHolder: Gloading.Holder

    /**
     * Delegate
     */
    protected val mDelegate: BaseViewDelegate by lazy {
        val delegate = BaseViewDelegate()
        delegate.holder = mHolder
        delegate
    }

    override fun showEmpty() {
        mDelegate.showEmpty()
    }

    override fun showLoadFailed(status: Int) {
        mDelegate.showLoadFailed(status)
    }

    override fun showLoading() {
        mDelegate.showLoading()
    }

    override fun showSuccess() {
        mDelegate.showSuccess()
    }

    override fun retry() {
        mDelegate.retry()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mHolder = Gloading.getDefault().wrap(onCreateContentView(inflater, container)).withRetry {
            retry()
        }
        return mHolder.wrapper
    }

    /**
     * 创建 Fragment 的 ContentView，如果继承该类必须实现此方法
     */
    abstract fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?): View

}
