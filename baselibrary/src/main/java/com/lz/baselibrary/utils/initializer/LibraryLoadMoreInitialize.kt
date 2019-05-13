package com.lz.baselibrary.utils.initializer

import android.content.Context
import com.lz.baselibrary.view.loadmore.SimpleLoadMoreViewConfig
import com.lz.baselibrary.view.loadmore.adapter.factory.DefaultLoadMoreItemViewAdapterFactory
import com.lz.baselibrary.view.loadmore.adapter.factory.LoadMoreItemViewAdapterFactory

/**
 * LoadMore 功能的初始化
 * @author linzheng
 */
class LibraryLoadMoreInitialize : SimpleInitialize() {

    override fun initial(context: Context) {
        sLoadMoreAdapterFactory = createDefaultFactory()
        sSimpleLoadMoreViewConfig = createDefaultLoadMoreViewConfig()
    }

    /**
     * 创建默认的 [SimpleLoadMoreViewConfig] 对象
     */
    open fun createDefaultLoadMoreViewConfig() = SimpleLoadMoreViewConfig()

    /**
     * 创建默认的 [DefaultLoadMoreItemViewAdapterFactory] 对象
     */
    open fun createDefaultFactory() = DefaultLoadMoreItemViewAdapterFactory()

    companion object {
        /**
         * LoadMoreAdapterFactory
         */
        lateinit var sLoadMoreAdapterFactory: LoadMoreItemViewAdapterFactory

        /**
         * LoadMoreViewConfig
         */
        lateinit var sSimpleLoadMoreViewConfig: SimpleLoadMoreViewConfig

    }

}