package com.lz.baselibrary.base

import com.lz.baselibrary.base.delegate.ListViewDelegate
import com.lz.baselibrary.view.refresh.Refresh
import me.drakeet.multitype.MultiTypeAdapter

/**
 * LibraryBaseListFragment
 * @author linzheng
 */
open abstract class LibraryBaseListFragment : LibraryBaseFragment(), ListView {


    /**
     * Refresh
     */
    protected open lateinit var mRefresh: Refresh

    /**
     * Delegate
     */
    private val mListViewDelegate: ListViewDelegate by lazy {
        val delegate = ListViewDelegate(mDelegate)
        delegate.refresh = mRefresh
        delegate
    }

    protected val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter
    }

    override fun refreshComplete() {
        mListViewDelegate.refreshComplete()
    }

    override fun refreshing() {
        mListViewDelegate.refreshing()
    }

}
