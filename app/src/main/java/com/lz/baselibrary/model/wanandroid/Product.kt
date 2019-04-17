package com.lz.baselibrary.model.wanandroid

import com.lz.baselibrary.view.itemdecoration.stickyheader.StickyHeader

/**
 * @author linzheng
 */
data class Product(
        val name: String,
        val typeName: String,
        val type: Int
) : StickyHeader {

    override val stickySortValue: String
        get() = type.toString()

    override val stickyShowValue: String
        get() = typeName
}