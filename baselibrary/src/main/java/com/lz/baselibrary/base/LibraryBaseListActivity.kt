package com.lz.baselibrary.base

import com.lz.baselibrary.base.delegate.ListViewDelegate
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
     * Delegate
     */
    private val mListViewDelegate: ListViewDelegate by lazy {
        val delegate = ListViewDelegate(mDelegate)
        if (this::mRefresh.isInitialized) delegate.refresh = mRefresh
        if (this::mLoadMore.isInitialized) delegate.loadMore = mLoadMore
        delegate
    }

    /**
     * Adapter
     */
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

    override fun loadMoreFail(code: Int) {
        mListViewDelegate.loadMoreFail(code)
    }

    override fun loadMoreNoMore() {
        mListViewDelegate.loadMoreNoMore()
    }

    override fun loadMoreNormal() {
        mListViewDelegate.loadMoreNormal()
    }

    override fun loadingMore() {
        mListViewDelegate.loadingMore()
    }

    override fun loadMoreReady() {
        mListViewDelegate.loadingMore()
    }

}
