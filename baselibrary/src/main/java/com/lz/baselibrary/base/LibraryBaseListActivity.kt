package com.lz.baselibrary.base

import android.view.View
import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener

/**
 * @author linzheng
 */
abstract class LibraryBaseListActivity<T : LibraryBaseListViewModel> : LibraryBaseActivity(), LoadMoreListener {

    protected abstract val mViewModel: T

    protected val mAdapter: LoadMoreAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = LoadMoreAdapter(this)
        adapter.items = mViewModel.mItems
        adapter
    }

    override fun loadMore(view: View) {
    }

}
