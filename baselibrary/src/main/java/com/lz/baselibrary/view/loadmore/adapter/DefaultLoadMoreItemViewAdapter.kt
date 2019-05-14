package com.lz.baselibrary.view.loadmore.adapter

import android.content.Context
import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.SimpleLoadMoreView
import com.lz.baselibrary.view.loadmore.RetryListener

/**
 * SimpleLoadMoreAdapter
 * @author linzheng
 */
class DefaultLoadMoreItemViewAdapter(
          context: Context,
          retryListener: RetryListener?
) : LoadMoreItemViewAdapter(context, retryListener) {

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

    override fun ready() {
        (mItemView as LoadMore).ready()
    }

}