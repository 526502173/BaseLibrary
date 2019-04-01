package com.lz.baselibrary.base

import androidx.fragment.app.Fragment
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

    override fun showEmpty() {
    }

    override fun showLoadFailed(status: Int) {
    }

    override fun showLoading() {
    }

    override fun showSuccess() {
    }
}
