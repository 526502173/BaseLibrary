package com.lz.baselibrary.view.adapter

import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.PagedListDiffer
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffPagedListAdapter(
        override val wrapperAdapter: MultiTypeAdapter
) : CommonDiffAdapter<PagedList<Any>>(wrapperAdapter) {

    override val differ: Differ<PagedList<Any>> by lazy {
        PagedListDiffer(this)
    }

    override fun submitList(pagedList: PagedList<Any>) {
        differ.submitList(pagedList)
    }

}