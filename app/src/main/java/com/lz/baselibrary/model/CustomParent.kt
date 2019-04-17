package com.lz.baselibrary.model

import com.lz.baselibrary.view.expand.Child
import com.lz.baselibrary.view.expand.Parent

/**
 * @author linzheng
 */
data class CustomParent(
        val title: String,
        val customChildList: List<Child>
) : Parent {
    override var isExpend: Boolean
        get() = false
        set(value) {}

    override fun getChildList(): List<Child> = customChildList
}