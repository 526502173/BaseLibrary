package com.lz.baselibrary.model

import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.model.expandlist.Parent

/**
 * @author linzheng
 */
data class CustomParent(
        val title: String,
        val customChildList: List<Child>
) : Parent() {
    override fun getChildList(): List<Child> = customChildList
}