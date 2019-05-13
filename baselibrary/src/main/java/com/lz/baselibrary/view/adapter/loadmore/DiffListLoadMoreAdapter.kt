package com.lz.baselibrary.view.adapter.loadmore

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.loadmore.delegate.LoadMoreAdapterDelegate
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Diff + LoadMore + MultiType
 * @author linzheng
 */
class DiffListLoadMoreAdapter(
        wrapperAdapter: MultiTypeAdapter,
        delegate: LoadMoreAdapterDelegate,
        differ: Differ<List<Any>>
) : CommonDiffLoadMoreAdapter<List<Any>>(wrapperAdapter, delegate, differ)