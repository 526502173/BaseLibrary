package com.lz.baselibrary.view.adapter

import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * 所有自定义 Adapter 的基类。
 * 如果想要使用提供 Adapter，必须要会 MultiTypeAdapter 的用法
 * @author linzheng
 */
open class BaseAdapter(
        private val wrapperAdapter: MultiTypeAdapter
) : MultiTypeAdapter() {

    override var types: Types
        get() = wrapperAdapter.types
        set(value) {
            wrapperAdapter.types = value
        }
}