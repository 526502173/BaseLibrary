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
        override val wrapperAdapter: MultiTypeAdapter,
        override val retry: () -> Unit
) : CommonDiffLoadMoreAdapter<PagedList<Any>>(
        wrapperAdapter,
        object : LoadMoreListener {
            override fun onLoadMore(view: View) {
                //ignore...
            }
        },
        retry
) {
    override val differ: Differ<PagedList<Any>> by lazy {
        PagedListDiffer(this)
    }
}