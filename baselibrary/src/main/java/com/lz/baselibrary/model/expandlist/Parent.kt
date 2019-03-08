package com.lz.baselibrary.model.expandlist

/**
 * @author linzheng
 */
open abstract class Parent{

    var isExpend: Boolean = false

    abstract fun getChildList(): List<Child>

}