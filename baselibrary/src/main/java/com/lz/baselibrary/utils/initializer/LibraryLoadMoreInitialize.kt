package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreAdapterFactory
import com.lz.baselibrary.view.loadmore.adapter.factory.SimpleLoadMoreAdapterFactory

/**
 * LoadMore 功能的初始化
 * @author linzheng
 */
class LibraryLoadMoreInitialize : SimpleInitialize() {

    override fun initial(context: Context) {
        sLoadMoreAdapterFactory = createDefaultFactory()
    }

    /**
     * 创建默认的 Factory 对象 [SimpleLoadMoreAdapterFactory]
     */
    open fun createDefaultFactory() = SimpleLoadMoreAdapterFactory()

    companion object {
        lateinit var sLoadMoreAdapterFactory: LoadMoreAdapterFactory
    }

}