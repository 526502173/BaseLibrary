package com.lz.baselibrary.view

import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import com.lz.baselibrary.view.itemdecoration.Top

/**
 * @author linzheng
 */
open class VerticalItemDecoration(
        override val padding: Int,
        override val color: Int
) : BaseItemDecoration(color, padding, Top())
