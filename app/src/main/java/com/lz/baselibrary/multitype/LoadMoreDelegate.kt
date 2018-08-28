package com.lz.baselibrary.multitype

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * RecyclerVIew 的加载更多
 * @author linzheng
 */

const val VISIBLE_THRESHOLD = 4

class LoadMoreDelegate(
        private val loadMoreListener: LoadMoreListener
) {

    private var mIsLoading = false

    fun attach(recyclerView: RecyclerView) {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(LoadMoreScrollListener(linearLayoutManager, loadMoreListener))
    }

    fun setLoading(isLoading: Boolean) {
        mIsLoading = isLoading
    }


    inner class LoadMoreScrollListener(
            private val layoutManager: LinearLayoutManager,
            private val loadMoreListener: LoadMoreListener
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (dy < 0 || mIsLoading) return
            val itemCount = layoutManager.itemCount;
            val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
            val isBottom = (lastVisiblePosition >= itemCount - VISIBLE_THRESHOLD)
            if (isBottom) {
                mIsLoading = true
                loadMoreListener.loadMore(recyclerView!!)
            }
        }

    }

}