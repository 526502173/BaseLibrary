package com.lz.baselibrary.base

import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
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
     * LoadMore
     */
    protected open lateinit var mLoadMore: LoadMore

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

    override fun loadMoreNoMore() {
        if (this::mLoadMore.isInitialized) mLoadMore.noMore()
    }

    override fun loadMoreNormal() {
        if (this::mLoadMore.isInitialized) mLoadMore.normal()
    }

}
