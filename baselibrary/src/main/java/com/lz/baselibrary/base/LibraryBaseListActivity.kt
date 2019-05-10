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
    protected open lateinit var refresh: Refresh

    /**
     * LoadMore
     */
    protected open lateinit var loadMore: LoadMore

    /**
     * Delegate
     */
    private val listViewDelegate: ListViewDelegate by lazy {
        val delegate = ListViewDelegate(baseViewDelegate)
        if (this::refresh.isInitialized) delegate.refresh = refresh
        if (this::loadMore.isInitialized) delegate.loadMore = loadMore
        delegate
    }

    /**
     * Adapter
     */
    protected val adapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter
    }

    override fun refreshComplete() {
        listViewDelegate.refreshComplete()
    }

    override fun refreshing() {
        listViewDelegate.refreshing()
    }

    override fun loadMoreFail(code: Int) {
        listViewDelegate.loadMoreFail(code)
    }

    override fun loadMoreNoMore() {
        listViewDelegate.loadMoreNoMore()
    }

    override fun disableLoadMore() {
        listViewDelegate.disableLoadMore()
    }

    override fun loadingMore() {
        listViewDelegate.loadingMore()
    }

    override fun loadMoreReady() {
        listViewDelegate.loadMoreReady()
    }

}
