package com.lz.baselibrary.view.adapter.diff

import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class PagedListDiffer(
        private val mAdapter: MultiTypeAdapter
) : SimpleDiffer<PagedList<Any>>() {

    private val mAsyncPagedListDiffer: AsyncPagedListDiffer<Any> by lazy {
        AsyncPagedListDiffer(mAdapter, MultiTypeDiffCallback(mAdapter))
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