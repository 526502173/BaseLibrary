package com.lz.baselibrary.view.adapter.loadmore

import androidx.paging.PagedList
import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Diff + Paging + LoadMore + MultiType
 * @author linzheng
 */
class DiffPagedListLoadMoreAdapter(
        wrapperAdapter: MultiTypeAdapter,
        delegate: LoadMoreAdapterDelegate,
        differ: Differ<PagedList<Any>>
) : CommonDiffLoadMoreAdapter<PagedList<Any>>(
        wrapperAdapter,
        delegate,
        differ
)