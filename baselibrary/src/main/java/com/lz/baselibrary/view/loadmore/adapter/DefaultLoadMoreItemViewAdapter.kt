package com.lz.baselibrary.view.loadmore.adapter

import android.content.Context
import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.SimpleLoadMoreView

/**
 * SimpleLoadMoreAdapter
 * @author linzheng
 */
class DefaultLoadMoreItemViewAdapter(
        override val context: Context,
        override val retry: () -> Unit
) : LoadMoreItemViewAdapter(context, retry) {

    override val mItemView: View by lazy {
        SimpleLoadMoreView.create(context, retry)
    }

    override fun noMore() {
        (mItemView as LoadMore).noMore()
    }

    override fun loading() {
        (mItemView as LoadMore).loading()
    }

    override fun disable() {
        (mItemView as LoadMore).disable()
    }

    override fun fail(code: Int) {
        (mItemView as LoadMore).fail(code)
    }

    override fun readly() {
        (mItemView as LoadMore).readly()
    }

}