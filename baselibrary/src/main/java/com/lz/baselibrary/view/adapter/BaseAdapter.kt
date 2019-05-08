package com.lz.baselibrary.view.adapter

import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.Types

/**
 * @author linzheng
 */
open class BaseAdapter(
        protected open val mWrapperAdapter: MultiTypeAdapter
) : MultiTypeAdapter() {

    override var types: Types
        get() = mWrapperAdapter.types
        set(value) {
            mWrapperAdapter.types = value
        }

}