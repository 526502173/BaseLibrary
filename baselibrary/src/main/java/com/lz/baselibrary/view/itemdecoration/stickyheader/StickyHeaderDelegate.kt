package com.lz.baselibrary.view.itemdecoration.stickyheader

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

/**
 * @author linzheng
 */
interface StickyHeaderDelegate<T : StickyHeader> {

    /**
     * 吸附的顶部高度
     */
    val headerHeight: Int

    /**
     * 绘制 Header 的画笔
     */
    val headerPaint: Paint

    /**
     * 数据源
     */
    var dataSource: List<T>

    /**
     * 绘制 Header
     * [bgRect] header 的绘制范围
     * [textRect] 要绘制的文字的 Rect
     */
    fun drawHeader(canvas: Canvas, bgRect: Rect, textRect: Rect, data: StickyHeader)

}