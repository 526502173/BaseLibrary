package com.lz.baselibrary.view.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author linzheng
 */
open class BaseItemDecoration(
        open val padding: Int,
        open val color: Int = Color.GRAY,
        private val directionList: List<DividerDirection>,
        open val isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean = { _, _ -> true }
) : RecyclerView.ItemDecoration() {

    /**
     * 用来绘制 Divider 的 Paint
     */
    protected val mPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        val paint = Paint()
        paint.color = color
        paint
    }

    /**
     * 绘制 Divider
     */
    protected fun drawDivider(canvas: Canvas, childView: View, padding: Int, direction: DividerDirection) {
        canvas.drawRect(createDividerRect(direction, childView, padding), mPaint)
    }

    /**
     * 根据 [direction] 创建不同的 Rect
     */
    private fun createDividerRect(direction: DividerDirection, childView: View, padding: Int) = when (direction) {
        is Top -> Rect(childView.left, childView.top - padding, childView.right, childView.top)
        is Bottom -> Rect(childView.left, childView.bottom, childView.right, childView.bottom + padding)
        is Right -> Rect(childView.left - padding, childView.top, childView.left, childView.bottom)
        is Left -> Rect(childView.right, childView.top, childView.right + padding, childView.bottom)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        directionList.forEach {
            when (it) {
                is Top -> outRect.set(0, padding, 0, 0)
                is Bottom -> outRect.set(0, 0, 0, padding)
                is Right -> outRect.set(0, 0, padding, 0)
                is Left -> outRect.set(padding, 0, 0, 0)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        repeat(parent.childCount) { index ->
            val childView = parent.getChildAt(index)
            directionList.forEach { if (isDrawDivider(it, index)) drawDivider(c, childView, padding, it) }
        }
    }

}
