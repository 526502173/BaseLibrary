package com.lz.baselibrary.base

import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/08
 * desc   : LibraryBaseListActivity
 * version: 1.0
</pre> *
 */
open class LibraryBaseListActivity : LibraryBaseActivity() {

    private val mItems: Items by lazy(LazyThreadSafetyMode.NONE) { Items() }

    private val mAdapter: MultiTypeAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MultiTypeAdapter()
        mAdapter.items = mItems
        mAdapter
    }

}
