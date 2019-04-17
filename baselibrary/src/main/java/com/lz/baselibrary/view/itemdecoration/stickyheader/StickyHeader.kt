package com.lz.baselibrary.view.itemdecoration.stickyheader

/**
 * @author linzheng
 */
interface StickyHeader{

    /**
     * 需要显示在 StickyHeader 上的值
     */
    val stickyShowValue: String

    /**
     * 数据源中数据根据这个值来进行分类
     */
    val stickySortValue: String

}