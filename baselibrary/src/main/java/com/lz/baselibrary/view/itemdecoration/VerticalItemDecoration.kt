package com.lz.baselibrary.view.itemdecoration

/**
 * @author linzheng
 */
open class VerticalItemDecoration(
        override val mPadding: Int,
        override val mColor: Int,
        override val mIsDrawDivider: (direction: DividerDirection, position: Int) -> Boolean
) : BaseItemDecoration(mColor, mPadding, listOf(Top))
