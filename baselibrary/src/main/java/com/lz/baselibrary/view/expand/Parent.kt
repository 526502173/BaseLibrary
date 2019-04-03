package com.lz.baselibrary.view.expand

/**
 * @author linzheng
 */
open abstract class Parent{

    var isExpend: Boolean = false

    abstract fun getChildList(): List<Child>

}