package com.lz.baselibrary.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/18
 * desc   : MySwipeRefreshLayout
 * version: 1.0
</pre> *
 */
class MySwipeRefreshLayout : SwipeRefreshLayout,RefreshLayout{

    private lateinit var mRecyclerView: RecyclerView

    constructor(ctx: Context):super(ctx)

    override fun onFinishInflate() {
        super.onFinishInflate()
        mRecyclerView = getChildAt(0) as RecyclerView
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var lastVisibleItem:Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState === RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerView?.adapter?.itemCount) {
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                lastVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }

        })
    }

    override fun refreshComplete() {
        refreshComplete()
    }

    override fun loadMoreComplete() {
        refreshComplete()
    }

    override fun refreshing() {
        refreshing()
    }

    override fun isLoadMoreEnable(isEnable: Boolean) {
    }

}
