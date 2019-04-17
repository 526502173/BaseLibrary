package com.lz.baselibrary.view.expand

/**
 * Parent 用于实现 2 级列表中的父 Item
 * @author linzheng
 */
interface Parent {

    var isExpend: Boolean

    fun getChildList(): List<Child>

}