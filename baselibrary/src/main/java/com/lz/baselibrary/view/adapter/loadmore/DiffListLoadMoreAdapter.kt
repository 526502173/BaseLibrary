package com.lz.baselibrary.view.adapter.loadmore

import com.lz.baselibrary.view.adapter.diff.Differ
import com.lz.baselibrary.view.adapter.diff.ListDiffer
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class DiffListLoadMoreAdapter(
        override val wrapperAdapter: MultiTypeAdapter,
        override val listener: LoadMoreListener,
        override val retry: () -> Unit
) : CommonDiffLoadMoreAdapter<List<Any>>(wrapperAdapter, listener, retry) {

    override val differ: Differ<List<Any>> by lazy {
        ListDiffer(this)
    }

}