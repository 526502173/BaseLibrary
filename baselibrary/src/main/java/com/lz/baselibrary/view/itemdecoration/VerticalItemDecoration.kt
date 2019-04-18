package com.lz.baselibrary.view.itemdecoration

import android.graphics.Rect

/**
 * @author linzheng
 */
open class VerticalItemDecoration(
        padding: Int,
        color: Int,
        isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean
) : BaseItemDecoration(
        mPadding = Rect(0, padding, 0, 0),
        mColor = color,
        mDirectionList = listOf(Top),
        mIsDrawDivider = isDrawDivider
)
