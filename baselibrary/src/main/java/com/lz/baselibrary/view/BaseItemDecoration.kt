package com.lz.baselibrary.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author linzheng
 */
open class BaseItemDecoration(
        open val color: Int = Color.GRAY,
        open val padding: Int,
        private vararg val direction: DividerDirection
) : RecyclerView.ItemDecoration() {

    protected val mPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        val paint = Paint()
        paint.color = color
        paint
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        direction.forEach {
            when(it){
                is Top -> outRect?.set(0,padding,0,0)
                is Bottom -> outRect?.set(0,0,0,padding)
                is Right -> outRect?.set(0,0,padding,0)
                is Left -> outRect?.set(padding,0,0,0)
            }
        }
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        direction.forEach {
            drawDivider(c!!,parent!!,padding,it)
        }
    }

    protected fun drawDivider(canvas: Canvas, parent: RecyclerView, padding: Int, direction: DividerDirection) {
        repeat(parent.childCount!!) {
            val childView = parent.getChildAt(it)
            canvas.drawRect(createDividerRect(direction, childView, padding), mPaint)
        }
    }

    private fun createDividerRect(direction: DividerDirection, childView: View, padding: Int) = when (direction) {
        is Top -> createTopDividerRect(childView, padding)
        is Bottom -> createBottomDividerRect(childView, padding)
        is Right -> createLeftDividerRect(childView, padding)
        is Left -> createRightDividerRect(childView, padding)
    }

    private fun createTopDividerRect(childView: View, padding: Int) = Rect(childView.left, childView.top-padding, childView.right, childView.top)

    private fun createBottomDividerRect(childView: View, padding: Int) = Rect(childView.left, childView.bottom , childView.right, childView.bottom+padding)

    private fun createLeftDividerRect(childView: View, padding: Int) = Rect(childView.left - padding, childView.top, childView.left, childView.bottom)

    private fun createRightDividerRect(childView: View, padding: Int) = Rect(childView.right, childView.top, childView.right + padding, childView.bottom)
}
