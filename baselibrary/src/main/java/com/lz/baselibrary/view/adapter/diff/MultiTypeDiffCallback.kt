package com.lz.baselibrary.view.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.MultiTypeAdapter

/**
 * 此类用来让 ItemViewBinder 实现 DiffCallback 的功能.
 * ItemViewBinder 需要继承 DiffItemViewBinder
 * @author linzheng
 */
class MultiTypeDiffCallback(
        private val adapter: MultiTypeAdapter
) : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem::class == newItem::class) {
            val itemViewBinder = getItemViewBinderByClazz(oldItem::class.java)
            itemViewBinder.areItemsTheSame(oldItem, newItem)
        } else false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem::class == newItem::class) {
            val itemViewBinder = getItemViewBinderByClazz(oldItem::class.java)
            itemViewBinder.areContentsTheSame(oldItem, newItem)
        } else false
    }

    private fun <T> getItemViewBinderByClazz(clazz: Class<T>): DiffItemViewBinder<Any, out RecyclerView.ViewHolder> {
        val index = adapter.types.firstIndexOf(clazz)
        val itemViewBinder = adapter.types.getType<Any>(index).binder
        return if (itemViewBinder is DiffItemViewBinder) itemViewBinder
        else throw ClassCastException("ItemViewBinder 必须要继承 DiffItemViewBinder")
    }

}