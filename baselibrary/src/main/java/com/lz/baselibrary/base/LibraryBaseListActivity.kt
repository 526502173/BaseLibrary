package com.lz.baselibrary.base

import android.view.View
import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class LibraryBaseListActivity<T : LibraryBaseListViewModel> : LibraryBaseActivity(), LoadMoreListener {

    protected abstract val mViewModel: T

    protected val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter.items = mViewModel.mItems
        adapter
    }

    override fun loadMore(view: View) {
    }

}
