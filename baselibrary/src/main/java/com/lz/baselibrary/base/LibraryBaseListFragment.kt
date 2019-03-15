package com.lz.baselibrary.base

import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import me.drakeet.multitype.MultiTypeAdapter

/**
 * LibraryBaseListFragment
 * @author linzheng
 */
open abstract class LibraryBaseListFragment<T : LibraryBaseListViewModel> : LibraryBaseFragment() {

    protected abstract val mViewModel: T

    protected val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter.items = mViewModel.mItems
        adapter
    }

}
