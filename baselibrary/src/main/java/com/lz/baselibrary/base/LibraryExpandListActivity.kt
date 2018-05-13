package com.lz.baselibrary.base

import android.view.View
import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent
import com.lz.baselibrary.utils.OnParentClickListener

/**
 * @author linzheng
 */
open class LibraryExpandListActivity<T : Parent> : LibraryBaseListActivity(), OnParentClickListener<T> {

    override fun onParentClick(v: View, parent: T) {
        var parentIndex = mItems.indexOf(parent)
        if (parentIndex == -1) return
        if (parent.isExpend) {
            mExpandItems[parent]?.forEach { mItems.remove(it) }
        } else {
            mExpandItems[parent]?.let { mItems.addAll(parentIndex + 1, it) }
            mAdapter.notifyItemInserted(parentIndex)
        }
        parent.isExpend = !parent.isExpend
        mAdapter.notifyDataSetChanged()
    }

    protected val mExpandItems: MutableMap<Parent, List<Child>> by lazy(LazyThreadSafetyMode.NONE) {
        HashMap<Parent, List<Child>>()
    }


}
