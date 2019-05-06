package com.lz.baselibrary.view.adapter.loadmore

import android.view.View
import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.PagedListDiffer
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffPagedListLoadMoreAdapter(
        override val mWrapperAdapter: MultiTypeAdapter,
        override val mRetry: () -> Unit
) : CommonDiffLoadMoreAdapter<PagedList<Any>>(
        mWrapperAdapter,
        object : LoadMoreListener {
            override fun onLoadMore(view: View) {
                //ignore...
            }
        },
        mRetry
) {
    override val mDiffer: Differ<PagedList<Any>> by lazy {
        PagedListDiffer(this)
    }

    override fun submitList(list: PagedList<Any>) {
        mDiffer.submitList(list)
    }
}