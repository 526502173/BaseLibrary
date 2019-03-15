package com.lz.baselibrary.base

import com.lz.baselibrary.base.viewmodel.LibraryBaseListViewModel
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class LibraryBaseListActivity<T : LibraryBaseListViewModel> : LibraryBaseActivity() {

    protected abstract val mViewModel: T

    protected val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter.items = mViewModel.mItems
        adapter
    }

}
