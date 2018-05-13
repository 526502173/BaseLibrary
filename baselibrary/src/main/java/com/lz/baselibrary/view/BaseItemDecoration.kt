package com.lz.baselibrary.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

/**
 * @author linzheng
 */
open class BaseItemDecoration(open val color: Int = Color.GRAY) : RecyclerView.ItemDecoration() {

    protected val mPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        val paint = Paint()
        paint.color = color
        paint
    }

    protected fun drawVertical(canvas: Canvas, parent: RecyclerView, padding: Int) {
        val left = 0
        val right = parent.width
        repeat(parent.childCount!!) {
            val childView = parent.getChildAt(it)
            val top = childView.bottom
            val bottom = top + padding
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }
    }

}
