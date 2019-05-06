package com.lz.baselibrary.view.adapter.submit

import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.diff.Differ

/**
 * @author linzheng
 */
class SubmitPagedListDelegate(
        private val mDiffer: Differ<PagedList<Any>>
) : SubmitDelegate<PagedList<Any>> {

    override fun submitList(list: PagedList<Any>) {
        mDiffer.submitList(list)
    }
}