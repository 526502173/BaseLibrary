package com.lz.baselibrary.view.adapter.loadmore

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.ListDiffer
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffListLoadMoreAdapter(
        override val mWrapperAdapter: MultiTypeAdapter,
        override val mListener: LoadMoreListener,
        override val mRetry: () -> Unit
) : CommonDiffLoadMoreAdapter<List<Any>>(mWrapperAdapter, mListener, mRetry) {

    override val mDiffer: Differ<List<Any>> by lazy {
        ListDiffer(this)
    }

    override fun submitList(list: List<Any>) {
        mDiffer.submitList(list)
    }
}