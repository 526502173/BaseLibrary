package com.lz.baselibrary.utils.expand

import android.support.v4.util.ArrayMap
import android.view.View
import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class ExpandAdapter<T : Parent>(
        private val items: Items,
        private val adapter: MultiTypeAdapter
) : OnParentClickListener<T> {

    val mExpandItems: ArrayMap<Parent, List<Child>> by lazy(LazyThreadSafetyMode.NONE) {
        ArrayMap<Parent, List<Child>>()
    }

    override fun onParentClick(v: View, parent: T) {
        var parentIndex = items.indexOf(parent)
        if (parentIndex == -1) return
        if (parent.isExpend) {
            mExpandItems[parent]?.forEach { items.remove(it) }
        } else {
            mExpandItems[parent]?.let { items.addAll(parentIndex + 1, it) }
            adapter.notifyItemInserted(parentIndex)
        }
        parent.isExpend = !parent.isExpend
        adapter.notifyDataSetChanged()
    }

    /**
     * @param list 一級的列表
     *             parent
     *              child
     *              child
     *              child
     *             parent
     *              child
     *              child
     *              child
     * @param isExpand 是否展开
     */
    fun init(list: List<Parent>, isExpand: Boolean = false) {
        list.forEach {
            it.isExpend = isExpand
            val childList = mutableListOf<Child>()
            it.getChildList().forEach { childList.add(it) }
            mExpandItems[it] = childList
            items.add(it)
        }
    }

}