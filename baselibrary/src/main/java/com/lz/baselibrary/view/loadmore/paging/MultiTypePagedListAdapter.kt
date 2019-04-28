package com.lz.baselibrary.view.loadmore.paging

import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * MultiTypePagedListAdapter 继承 MultiType，既可以对接
 * Jetpack 中的 Paging 又可以拥有 MultiType 的特性
 * @author linzheng
 */
open class MultiTypePagedListAdapter : MultiTypeAdapter(), SubmitListAdapter<Any> {

    private val mDiffer: AsyncPagedListDiffer<Any> by lazy {
        AsyncPagedListDiffer<Any>(this, MultiTypeDiffCallback(types))
    }

    override var items: List<Any>
        get() = mDiffer.currentList ?: emptyList()
        set(value) {
            //使用 Paging 不需要调用 setItems 方法.
        }

    override fun getItemCount(): Int {
        return mDiffer.itemCount
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = getItem(position)
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    override fun submitList(pagedList: PagedList<Any>) {
        mDiffer.submitList(pagedList)
    }

    open fun getItem(position: Int) = mDiffer.getItem(position)!!

    open fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        return types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    class MultiTypeDiffCallback(
            private val types: Types
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
            val index = types.firstIndexOf(clazz)
            val itemViewBinder = types.getType<Any>(index).binder
            return if (itemViewBinder is DiffItemViewBinder) itemViewBinder
            else throw ClassCastException("ItemViewBinder 必须要继承 DiffItemViewBinder")
        }

    }

}