package com.lz.baselibrary.multitype

import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerVIew 的加载更多
 * @author linzheng
 */

const val VISIBLE_THRESHOLD = 4

class LoadMoreDelegate(
        private val loadMoreListener: LoadMoreListener
) {

    private var mIsLoading = false

    fun attach(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        val linearLayoutManager = recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
        recyclerView.addOnScrollListener(LoadMoreScrollListener(linearLayoutManager, loadMoreListener))
    }

    fun setLoading(isLoading: Boolean) {
        mIsLoading = isLoading
    }


    inner class LoadMoreScrollListener(
            private val layoutManager: androidx.recyclerview.widget.LinearLayoutManager,
            private val loadMoreListener: LoadMoreListener
    ) : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
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