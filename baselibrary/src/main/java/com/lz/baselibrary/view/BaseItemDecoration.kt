package com.lz.baselibrary.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

/**
 * <pre>
 *     author : Think
 *     e-mail : 1007687534@qq.com
 *     time   : 2018/02/09
 *     desc   : BaseItemDecoration
 *     version: 1.0
 * </pre>
 */
open class BaseItemDecoration() : RecyclerView.ItemDecoration() {

    val mPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        val paint = Paint()
        paint.color = Color.GRAY
        paint
    }

    protected fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = 0
        val right = parent.width
        repeat(parent.childCount!!) {
            val childView = parent.getChildAt(it)
            val top = childView.bottom
            val bottom = top + 10
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }
    }

}