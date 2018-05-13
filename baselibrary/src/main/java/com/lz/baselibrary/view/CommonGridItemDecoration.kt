package com.lz.baselibrary.view

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author linzheng
 */
open class CommonGridItemDecoration(private val padding: Int, override val color: Int) : BaseItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        val position = parent!!.getChildLayoutPosition(view)
        val paddingHalf = padding.shr(1)
        outRect?.bottom = padding
        outRect?.right = if (position % 2 == 1) padding else paddingHalf
        outRect?.left = if (position % 2 == 1) paddingHalf else padding
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        // TODO 待实现
    }

}