package com.lz.baselibrary.view.adapter

import com.lz.baselibrary.view.itemdecoration.loadmore.LoadMoreListener
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * 所有自定义 Adapter 的基类。
 * 如果想要使用提供 Adapter，必须要会 MultiTypeAdapter 的用法
 * @author linzheng
 */
open abstract class BaseAdapter(
        private val wrapperAdapter: MultiTypeAdapter
) : MultiTypeAdapter() {

    override var types: Types
        get() = wrapperAdapter.types
        set(value) {
            wrapperAdapter.types = value
        }

    /**
     * LoadMore 的监听器
     */
    //todo 将此方法以接口的方式声明
    abstract fun setLoadMoreListener(listener: LoadMoreListener)
}