package com.lz.baselibrary.utils.expand

import android.view.View
import androidx.collection.ArrayMap
import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent
import me.drakeet.multitype.MultiTypeAdapter

/**
 * 实现一个二级列表 Adapter
 * 扩展 MultiType 中 MultTypeAdapter
 * @author linzheng
 */
class ExpandAdapter<T : Parent>(
        private val items: ArrayList<Any>,
        private val adapter: MultiTypeAdapter
) : OnParentClickListener<T> {

    private var mIsInitialize = false

    private val mExpandItems: ArrayMap<Parent, List<Child>> by lazy(LazyThreadSafetyMode.NONE) {
        ArrayMap<Parent, List<Child>>()
    }

    /**
     * ParentItem 的点击事件
     */
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
     * 初始化方法
     * @param list ParentList
     * @param isExpand 是否展开
     */
    fun init(list: List<Parent>, isExpand: Boolean = false) {
        if (mIsInitialize) return
        mIsInitialize = !mIsInitialize
        list.forEach {
            it.isExpend = isExpand
            val childList = mutableListOf<Child>()
            it.getChildList().forEach { childList.add(it) }
            mExpandItems[it] = childList
            items.add(it)
        }
    }

}