package com.lz.baselibrary.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * @author linzheng
 */
class MySwipeRefreshLayout : SwipeRefreshLayout, RefreshLayout {

    private lateinit var mRecyclerView: RecyclerView

    private var mIsLoadMoreEnable: Boolean = true

    private var mIsRefresh: Boolean = true

    private lateinit var mRefreshListener: RefreshListener

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    @SuppressLint("MissingSuperCall")
    override fun onFinishInflate() {
        super.onFinishInflate()
        mRecyclerView = getChildAt(1) as RecyclerView
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var lastVisibleItem: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (mIsLoadMoreEnable
                        && newState === RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerView?.adapter?.itemCount) {
                    if (!isRefreshing) {
                        mIsRefresh = false
                        isRefreshing = true
                        if (this@MySwipeRefreshLayout::mRefreshListener.isInitialized) mRefreshListener.refresh(mIsRefresh)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                lastVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }

        })
        setOnRefreshListener {
            if (!mIsLoadMoreEnable) mIsLoadMoreEnable = !mIsLoadMoreEnable
            if (this::mRefreshListener.isInitialized) mRefreshListener.refresh(mIsRefresh)
        }
    }

    override fun refreshComplete() {
        mIsRefresh = true
        isRefreshing = false
    }

    override fun refreshing() {
        isRefreshing = true
    }

    override fun isLoadMoreEnable(isEnable: Boolean) {
        mIsLoadMoreEnable = isEnable
    }

    fun setRefreshListener(listener: RefreshListener) {
        mRefreshListener = listener
    }


}
