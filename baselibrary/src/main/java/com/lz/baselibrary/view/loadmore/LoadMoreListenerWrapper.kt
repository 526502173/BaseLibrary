package com.lz.baselibrary.view.loadmore

import android.view.View
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMore
import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener

/**
 * @author linzheng
 */
class LoadMoreListenerWrapper(
        private val listener: LoadMoreListener,
        private val loadMore: LoadMore
) : LoadMoreListener {
    override fun onLoadMore(view: View) {
        listener.onLoadMore(view)
        //这里必须使用 post() 方法来执行 loading() 方法，因为 onLoadMore() 方法是在 Adapter 的 onBinderViewHolder() 中执行的。
        //在 Adapter 的 onBinderViewHolder() 中不能调用 notify() 方法。
        view.post {
            loadMore.loading()
        }
    }
}