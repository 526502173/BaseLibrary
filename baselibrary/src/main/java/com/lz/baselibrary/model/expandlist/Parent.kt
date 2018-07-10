package com.lz.baselibrary.model.expandlist

/**
 * @author linzheng
 */
open abstract class Parent{

    var isExpend: Boolean = false

    abstract fun getChildList(): List<Child>

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

}