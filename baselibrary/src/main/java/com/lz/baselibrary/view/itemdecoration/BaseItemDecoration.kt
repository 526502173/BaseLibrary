package com.lz.baselibrary.view.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

/**
 * @author linzheng
 */
//todo mPadding 修改为 list 类型，和 mDirectionList 对应起来
open class BaseItemDecoration(
        open val mPadding: Int = 1,
        open val mColor: Int = Color.GRAY,
        open val mDirectionList: List<DividerDirection> = emptyList(),
        open val mIsDrawDivider: (direction: DividerDirection, position: Int) -> Boolean = { _, _ -> true }
) : RecyclerView.ItemDecoration() {

    /**
     * 用来绘制 Divider 的 Paint
     */
    protected val mPaint: Paint by lazy(LazyThreadSafetyMode.NONE) {
        val paint = Paint()
        paint.color = mColor
        paint
    }

    /**
     * 绘制 Divider
     */
    fun drawDivider(canvas: Canvas, childView: View, index: Int) {
        mDirectionList.forEach {
            if (mIsDrawDivider(it, index)) canvas.drawRect(createDividerRect(it, childView, mPadding), mPaint)
        }
    }

    /**
     * 根据 [direction] 创建不同的 Rect
     */
    protected fun createDividerRect(direction: DividerDirection, childView: View, padding: Int) = when (direction) {
        is Top -> Rect(childView.left, childView.top - padding, childView.right, childView.top)
        is Bottom -> Rect(childView.left, childView.bottom, childView.right, childView.bottom + padding)
        is Right -> Rect(childView.left - padding, childView.top, childView.left, childView.bottom)
        is Left -> Rect(childView.right, childView.top, childView.right + padding, childView.bottom)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        mDirectionList.forEach {
            when (it) {
                is Top -> outRect.set(0, mPadding, 0, 0)
                is Bottom -> outRect.set(0, 0, 0, mPadding)
                is Right -> outRect.set(0, 0, mPadding, 0)
                is Left -> outRect.set(mPadding, 0, 0, 0)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children.forEachIndexed { index, child ->
            drawDivider(c, child, index)
        }
    }


}
