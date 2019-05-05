package com.lz.baselibrary.view.loadmore.diff.paging

import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.loadmore.diff.MultiTypeDiffCallback
import com.lz.baselibrary.view.loadmore.diff.SubmitPagedListAdapter
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * 给普通的 MultiTypeAdapter 扩展出 PagedList 的处理能力
 * @author linzheng
 */
open class PagedListAdapterWrapper(
        private val mWrapperAdapter: MultiTypeAdapter
) : MultiTypeAdapter(), SubmitPagedListAdapter<Any> {

    private val mDiffer: AsyncPagedListDiffer<Any> by lazy {
        AsyncPagedListDiffer<Any>(this, MultiTypeDiffCallback(types))
    }

    override var types: Types
        get() = mWrapperAdapter.types
        set(value) {}

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
}