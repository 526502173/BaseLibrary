package com.lz.baselibrary.view.loadmore.diff

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * @author linzheng
 */
open class DiffAdapterWrapper(
        open val mWrapperAdapter: MultiTypeAdapter
) : MultiTypeAdapter(), SubmitListAdapter<Any> {

    private val mDiffer: AsyncListDiffer<Any> by lazy {
        AsyncListDiffer(this, MultiTypeDiffCallback(types))
    }

    override var types: Types
        get() = mWrapperAdapter.types
        set(value) {}

    override var items: List<Any>
        get() = mDiffer.currentList
        set(value) {
            //使用 Paging 不需要调用 setItems 方法.
        }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        val item = getItem(position)
        getOutBinderByViewHolder(holder).onBindViewHolder(holder, item, payloads)
    }

    open fun getItem(position: Int): Any = mDiffer.currentList[position]

    open fun getOutBinderByViewHolder(holder: RecyclerView.ViewHolder): ItemViewBinder<Any, RecyclerView.ViewHolder> {
        return types.getType<Any>(holder.itemViewType).binder as ItemViewBinder<Any, RecyclerView.ViewHolder>
    }

    override fun submitList(list: List<Any>) {
        mDiffer.submitList(list)
    }
}