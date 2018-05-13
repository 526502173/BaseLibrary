package com.lz.baselibrary.view

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author linzheng
 */
open class VerticalItemDecoration(private val padding: Int, override val color: Int) : BaseItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.set(0, 0, 0, padding)
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        drawVertical(c!!, parent!!, padding)
    }

}
