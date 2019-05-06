package com.lz.baselibrary.view.adapter.submit

import com.lz.baselibrary.view.adapter.diff.Differ

/**
 * @author linzheng
 */
class SubmitListDelegate(
        private val mDiffer: Differ<List<Any>>
) : SubmitDelegate<List<Any>> {
    override fun submitList(list: List<Any>) {
        mDiffer.submitList(list)
    }
}