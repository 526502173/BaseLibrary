package com.lz.baselibrary.view.itemdecoration.stickyheader

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration

/**
 * 顶部吸附的 ItemViewDecoration
 * @author linzheng
 */
class StickyHeaderItemDecoration<T : StickyHeader>(
        private val isSticky: Boolean = true,
        private val delegate: StickyHeaderDelegate<T>,
        private val baseItemDecoration: BaseItemDecoration? = BaseItemDecoration.createFromTop(1, Color.GRAY)
) : RecyclerView.ItemDecoration(), StickyHeaderDelegate<T> by delegate {

    /**
     * Header 的绘制范围
     */
    private val mHeaderRect by lazy { Rect() }

    /**
     * 用于存储文字的参数
     */
    private val mTextRect: Rect by lazy { Rect() }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.children.forEachIndexed { index, child ->
            val position = parent.getChildAdapterPosition(child)
            if (isLastItemDiff(position)) {
                val stickyValue = dataSource[position].stickyShowValue
                setHeaderRect(child, parent)
                headerPaint.getTextBounds(stickyValue, 0, stickyValue.length, mTextRect)
                drawHeader(c, mHeaderRect, mTextRect, dataSource[position])
            } else baseItemDecoration?.drawDivider(c, child, index)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (!isSticky) return
        val layoutManager = parent.layoutManager as? LinearLayoutManager
                ?: throw ClassCastException("RecyclerView 的 LayoutManger 必须为 LinearLayoutManager 类型")
        val firstPosition = layoutManager.findFirstVisibleItemPosition()
        val firstChild = parent.findViewHolderForAdapterPosition(firstPosition)?.itemView
        //更具 findViewHolderForAdapterPosition 方法中的描述得知，这个方法有一定几率会返回 null
        if (firstChild != null) {
            var flag = false
            if (isNextItemDiff(firstPosition) && isNeedTranslate(firstChild)) {
                c.save()
                flag = true
                val dy = (firstChild.top + firstChild.height - headerHeight).toFloat()
                c.translate(0f, dy)
            }
            setHeaderRect(firstChild, parent, true)
            drawHeader(c, mHeaderRect, mTextRect, dataSource[firstPosition])
            if (flag) c.restore()
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (isLastItemDiff(position)) outRect.set(0, headerHeight, 0, 0)
        else baseItemDecoration?.getItemOffsets(outRect, view, parent, state)
    }

    /**
     * 判断是否需要执行位移
     */
    private fun isNeedTranslate(child: View): Boolean {
        return child.top + child.height < headerHeight
    }

    /**
     * 设置 mHeaderRect 中的值
     */
    private fun setHeaderRect(child: View, parent: RecyclerView, isCover: Boolean = false) {
        val params = child.layoutParams as RecyclerView.LayoutParams
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val top = if (isCover) parent.top else child.top - params.topMargin - headerHeight
        val bottom = if (isCover) parent.top + headerHeight else child.top - params.topMargin
        mHeaderRect.set(left, top, right, bottom)
    }

    /**
     * 是否和上一个 Item 的 stickySortValue 不同
     * 如果 [position] == 0 则直接返回 true
     * @return true 表示不相同 false 表示相同
     */
    private fun isLastItemDiff(position: Int): Boolean {
        return if (position == 0) true
        else {
            val item = dataSource[position].stickySortValue
            val lastItem = dataSource[position - 1].stickySortValue
            item != lastItem
        }
    }

    /**
     * 是否和下一个 Item 的 stickySortValue 不同
     * 如果 [position] == lastIndex 则直接返回 false
     * @return true 表示不相同 false 表示相同
     */
    private fun isNextItemDiff(position: Int): Boolean {
        return if (position == dataSource.lastIndex) false
        else {
            val item = dataSource[position].stickySortValue
            val lastItem = dataSource[position + 1].stickySortValue
            item != lastItem
        }
    }
}