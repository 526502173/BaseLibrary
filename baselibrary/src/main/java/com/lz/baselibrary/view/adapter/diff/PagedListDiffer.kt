package com.lz.baselibrary.view.adapter.diff

import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList

/**
 * @author linzheng
 */
class PagedListDiffer : SimpleDiffer<PagedList<Any>>() {

    override fun submitList(newList: PagedList<Any>, callback: Runnable) {
        mAsyncPagedListDiffer.submitList(newList, callback)
    }

    private val mAsyncPagedListDiffer: AsyncPagedListDiffer<Any> by lazy {
        if (adapter == null) throw IllegalArgumentException("adapter is not initialized!")
        AsyncPagedListDiffer(adapter, MultiTypeDiffCallback(adapter))
    }

    override fun submitList(newList: PagedList<Any>) {
        mAsyncPagedListDiffer.submitList(newList)
    }

    override val currentList: List<Any>
        get() = mAsyncPagedListDiffer.currentList ?: emptyList()

    override fun getItem(position: Int): Any? {
        return mAsyncPagedListDiffer.getItem(position)
    }

    override fun getItemCount(): Int {
        return mAsyncPagedListDiffer.itemCount
    }
}