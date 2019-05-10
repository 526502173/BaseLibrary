package com.lz.baselibrary.view.adapter

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.ListDiffer
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffListAdapter(
        override val wrapperAdapter: MultiTypeAdapter
) : CommonDiffAdapter<List<Any>>(wrapperAdapter) {

    override val differ: Differ<List<Any>> by lazy {
        ListDiffer(this)
    }

}