package com.lz.baselibrary.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * LibraryBaseFragment
 * @author linzheng
 */
open abstract class LibraryBaseFragment : Fragment(), BaseView {

    /**
     *  Fragment 依附的 Activity
     */
    private lateinit var mBaseView: BaseView

    /**
     * AutoDispose
     */
    protected val mScopeProvider: AndroidLifecycleScopeProvider by lazy(LazyThreadSafetyMode.NONE) {
        AndroidLifecycleScopeProvider.from(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mBaseView = context as BaseView
    }

    override fun showEmpty() {
    }

    override fun showLoadFailed() {
    }

    override fun showLoading() {
    }

    override fun showSuccess() {
    }
}
