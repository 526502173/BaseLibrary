package com.lz.baselibrary.view.loadmore

import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener

/**
 * @author linzheng
 */
class LoadMoreListenerWrapper(
        private val mListener: LoadMoreListener,
        private val mLoadMore: LoadMore
) : LoadMoreListener {
    override fun onLoadMore(view: View) {
        mListener.onLoadMore(view)
        //这里必须使用 post() 方法来执行 loading() 方法，因为 onLoadMore() 方法是在 Adapter 的 onBinderViewHolder() 中执行的。
        //在 Adapter 的 onBinderViewHolder() 中不能调用 notify() 方法。
        view.post {
            mLoadMore.loading()
        }
    }
}