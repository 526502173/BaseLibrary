package com.lz.baselibrary.view.adapter

import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.diff.Differ
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Diff + PagedList + MultiType
 * @author linzheng
 */
class DiffPagedListAdapter(
        wrapperAdapter: MultiTypeAdapter,
        differ: Differ<PagedList<Any>>
) : CommonDiffAdapter<PagedList<Any>>(wrapperAdapter, differ)