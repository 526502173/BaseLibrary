package com.lz.baselibrary.view.loadmore

import android.content.Context
import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.SimpleLoadMoreView

/**
 * SimpleLoadMoreAdapter
 * @author linzheng
 */
class SimpleLoadMoreAdapter(override val mContext: Context) : LoadMoreAdapter(mContext) {

    override val mItemView: View by lazy {
        SimpleLoadMoreView.create(mContext)
    }

    override fun noMore() {
        (mItemView as LoadMore).noMore()
    }

    override fun loading() {
        (mItemView as LoadMore).loading()
    }

    override fun normal() {
        (mItemView as LoadMore).normal()
    }

}