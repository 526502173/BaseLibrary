package com.lz.baselibrary.view.adapter

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.ListDiffer
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffListAdapter(
        override val mWrapperAdapter: MultiTypeAdapter
) : CommonDiffAdapter<List<Any>>(mWrapperAdapter) {

    override val mDiffer: Differ<List<Any>> by lazy {
        ListDiffer(this)
    }

}