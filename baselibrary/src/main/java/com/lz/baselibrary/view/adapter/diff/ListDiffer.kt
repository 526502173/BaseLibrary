package com.lz.baselibrary.view.adapter.diff

import androidx.recyclerview.widget.AsyncListDiffer
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class ListDiffer(
        private val adapter: MultiTypeAdapter
) : SimpleDiffer<List<Any>>() {

    override fun submitList(newList: List<Any>, callback: Runnable) {
        mAsyncListDiffer.submitList(newList, callback)
    }

    private val mAsyncListDiffer: AsyncListDiffer<Any> by lazy {
        AsyncListDiffer(adapter, MultiTypeDiffCallback(adapter))
    }

    override fun submitList(newList: List<Any>) {
        mAsyncListDiffer.submitList(newList)
    }

    override val currentList: List<Any>
        get() = mAsyncListDiffer.currentList
}