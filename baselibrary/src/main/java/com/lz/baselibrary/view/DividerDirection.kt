package com.lz.baselibrary.view

/**
 * 分隔线方向
 * @author linzheng
 */
sealed class DividerDirection

class Top: DividerDirection()

class Bottom: DividerDirection()

class Left: DividerDirection()

class Right: DividerDirection()