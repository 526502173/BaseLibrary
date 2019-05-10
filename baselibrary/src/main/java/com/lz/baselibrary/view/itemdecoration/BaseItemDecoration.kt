package com.lz.baselibrary.view.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseItemDecoration 封装了 ItemDecoration 的基本操作
 * @author linzheng
 */
open class BaseItemDecoration private constructor(
        open val padding: Rect = Rect(),
        open val color: Int = Color.GRAY,
        open val directionList: List<DividerDirection> = emptyList(),
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
    fun drawDivider(canvas: Canvas, childView: View, index: Int) {
        directionList.forEach {
            if (isDrawDivider(it, index)) canvas.drawRect(createDividerRect(it, childView, padding), mPaint)
        }
    }

    /**
     * 根据 [direction] 创建不同的 Rect
     */
    protected fun createDividerRect(direction: DividerDirection, childView: View, padding: Rect) = when (direction) {
        is Top -> Rect(childView.left, childView.top - padding.top, childView.right, childView.top)
        is Bottom -> Rect(childView.left, childView.bottom, childView.right, childView.bottom + padding.bottom)
        is Right -> Rect(childView.left - padding.right, childView.top, childView.left, childView.bottom)
        is Left -> Rect(childView.right, childView.top, childView.right + padding.left, childView.bottom)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        directionList.forEach {
            when (it) {
                is Top -> outRect.set(0, padding.top, 0, 0)
                is Bottom -> outRect.set(0, 0, 0, padding.bottom)
                is Right -> outRect.set(0, 0, padding.right, 0)
                is Left -> outRect.set(padding.left, 0, 0, 0)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children.forEachIndexed { index, child ->
            drawDivider(c, child, index)
        }
    }

    companion object {

        /**
         * 创建一个只有 Top 的 BaseItemDecoration
         */
        fun createFromTop(padding: Int, color: Int, isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean = { _, _ -> true }) = BaseItemDecoration(
                padding = Rect(0, padding, 0, 0),
                color = color,
                directionList = listOf(Top),
                isDrawDivider = isDrawDivider
        )

        /**
         * 创建一个只有 Bottom 的 BaseItemDecoration
         */
        fun createFromBottom(padding: Int, color: Int, isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean = { _, _ -> true }) = BaseItemDecoration(
                padding = Rect(0, 0, 0, padding),
                color = color,
                directionList = listOf(Bottom),
                isDrawDivider = isDrawDivider
        )

        /**
         * 创建一个只有 Left 的 BaseItemDecoration
         */
        fun createFromLeft(padding: Int, color: Int, isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean = { _, _ -> true }) = BaseItemDecoration(
                padding = Rect(padding, 0, 0, 0),
                color = color,
                directionList = listOf(Left),
                isDrawDivider = isDrawDivider
        )


        /**
         * 创建一个只有 Right 的 BaseItemDecoration
         */
        fun createFromRight(padding: Int, color: Int, isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean = { _, _ -> true }) = BaseItemDecoration(
                padding = Rect(0, 0, padding, 0),
                color = color,
                directionList = listOf(Right),
                isDrawDivider = isDrawDivider
        )

    }


}
