package com.lz.baselibrary.base

import com.lz.baselibrary.view.refresh.Refresh
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class LibraryBaseListActivity : LibraryBaseActivity(), ListView {

    /**
     * Refresh
     */
    protected open lateinit var mRefresh: Refresh

    /**
     * Adapter
     */
    protected val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter
    }

    override fun refreshComplete() {
        if (this::mRefresh.isInitialized) mRefresh.complete()
    }

    override fun refreshing() {
        if (this::mRefresh.isInitialized) mRefresh.refreshing()
    }
}
