package com.lz.baselibrary.view.itemdecoration

/**
 * 分隔线方向
 * @author linzheng
 */
sealed class DividerDirection

object Top : DividerDirection()

object Bottom : DividerDirection()

object Left : DividerDirection()

object Right : DividerDirection()