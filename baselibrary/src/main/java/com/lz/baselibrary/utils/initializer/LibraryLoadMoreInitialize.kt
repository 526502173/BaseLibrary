package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.lz.baselibrary.view.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.loadmore.SimpleLoadMoreAdapter

/**
 * LoadMore 功能的初始化
 * @author linzheng
 */
class LibraryLoadMoreInitialize : SimpleInitialize() {

    override fun initial(context: Context) {
        sLoadMoreAdapter = createLoadMoreAdapter(context)
    }

    open fun createLoadMoreAdapter(context: Context) = SimpleLoadMoreAdapter(context)

    companion object {
        lateinit var sLoadMoreAdapter: LoadMoreAdapter
    }

}