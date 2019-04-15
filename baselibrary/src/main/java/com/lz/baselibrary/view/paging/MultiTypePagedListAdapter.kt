package com.lz.baselibrary.view.paging

import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter

/**
 * MultiTypePagedListAdapter 继承 MultiType，既可以对接
 * Jetpack 中的 Paging 又可以拥有 MultiType 的特性
 * @author linzheng
 */
class MultiTypePagedListAdapter : MultiTypeAdapter() {

    private var mDiffer: AsyncPagedListDiffer<Any> = AsyncPagedListDiffer<Any>(this, MultiTypeDiffCallback())

    override var items: List<Any>
        get() = mDiffer.currentList ?: emptyList()
        set(value) {}

    override fun getItemCount(): Int {
        return mDiffer.itemCount
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = mDiffer.getItem(position)!!
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    fun submitList(pagedList: PagedList<Any>) {
        mDiffer.submitList(pagedList)
    }

    private fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        return types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    inner class MultiTypeDiffCallback : DiffUtil.ItemCallback<Any>() {

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
            val index = types.firstIndexOf(clazz)
            val itemViewBinder = types.getType<Any>(index).binder
            return if (itemViewBinder is DiffItemViewBinder) itemViewBinder
            else throw ClassCastException("ItemViewBinder 必须要继承 DiffItemViewBinder")
        }

    }

}