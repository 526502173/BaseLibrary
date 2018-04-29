package com.lz.baselibrary.base

import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
open class LibraryBaseListActivity : LibraryBaseActivity() {

    protected val mItems: Items by lazy(LazyThreadSafetyMode.NONE) { Items() }

    protected val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val adapter = MultiTypeAdapter()
        adapter.items = mItems
        adapter
    }

    protected var mPage: Int = 1

}
