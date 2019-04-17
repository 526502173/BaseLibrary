package com.lz.baselibrary.view.itemdecoration.stickyheader

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.lz.baselibrary.LibraryApplication
import com.lz.baselibrary.dp2px

/**
 * @author linzheng
 */
open class DefaultStickyHeaderDelegate<T : StickyHeader>(
        override var dataSource: List<T>
) : StickyHeaderDelegate<T> {

    override val headerHeight by lazy {
        30.dp2px(LibraryApplication.app())
    }

    override val headerPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            textSize = 30f
        }
    }

    override fun drawHeader(canvas: Canvas, rect: Rect, textRect: Rect, data: StickyHeader) {
        headerPaint.color = COLOR_HEADER_BG
        canvas.drawRect(rect, headerPaint)
        headerPaint.color = COLOR_HEADER_TEXT
        val x = rect.left.toFloat() + HEADER_TEXT_PADDING
        val y = rect.bottom - rect.height().shr(1) + textRect.height().shr(1).toFloat()
        canvas.drawText(data.stickyShowValue, x, y, headerPaint)
    }

    companion object {

        private val COLOR_HEADER_BG = Color.parseColor("#EEEEEE")

        private val COLOR_HEADER_TEXT = Color.parseColor("#242424")

        private val HEADER_TEXT_PADDING = 10.dp2px(LibraryApplication.app())

    }

}