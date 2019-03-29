package com.lz.baselibrary.view.itemdecoration

/**
 * @author linzheng
 */
open class VerticalItemDecoration(
        override val padding: Int,
        override val color: Int,
        override val isDrawDivider: (direction: DividerDirection, position: Int) -> Boolean
) : BaseItemDecoration(color, padding, listOf(Top))
