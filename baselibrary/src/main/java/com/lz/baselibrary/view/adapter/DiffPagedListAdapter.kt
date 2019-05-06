package com.lz.baselibrary.view.adapter

import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.PagedListDiffer
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffPagedListAdapter(
        override val mWrapperAdapter: MultiTypeAdapter
) : CommonDiffAdapter<PagedList<Any>>(mWrapperAdapter) {

    override val mDiffer: Differ<PagedList<Any>> by lazy {
        PagedListDiffer(this)
    }

    override fun submitList(pagedList: PagedList<Any>) {
        mDiffer.submitList(pagedList)
    }

}