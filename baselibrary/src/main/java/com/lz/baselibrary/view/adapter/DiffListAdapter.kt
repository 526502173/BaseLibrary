package com.lz.baselibrary.view.adapter

import com.lz.baselibrary.view.adapter.diff.Differ
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Diff + MultiType
 * @author linzheng
 */
class DiffListAdapter(
        wrapperAdapter: MultiTypeAdapter,
        differ: Differ<List<Any>>
) : CommonDiffAdapter<List<Any>>(wrapperAdapter, differ)