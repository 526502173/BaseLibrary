package com.lz.baselibrary

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lz.baselibrary.base.LibraryExpandListActivity
import com.lz.baselibrary.model.CustomChild
import com.lz.baselibrary.model.CustomParent
import com.lz.baselibrary.model.expandlist.Child
import com.lz.baselibrary.multitype.CustomChildItemViewBinder
import com.lz.baselibrary.multitype.CustomParentItemViewBinder
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_expand_list.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class ExpandListActivity : LibraryExpandListActivity<CustomParent>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_list)
        LibraryApplication.getInstance()
        repeat(20) {
            val childList = mutableListOf<Child>()
            repeat(5) {
                childList.add(CustomChild())
            }
            mExpandItems[CustomParent()] = childList
        }
        mExpandItems.keys.forEach {
            mItems.add(it)
        }

        mAdapter.register(CustomParent::class, CustomParentItemViewBinder(this))
        mAdapter.register(CustomChild::class, CustomChildItemViewBinder())

        rv_expand_list.layoutManager = LinearLayoutManager(this)
        rv_expand_list.addItemDecoration(VerticalItemDecoration(2, Color.GRAY))
        rv_expand_list.adapter = mAdapter

    }

}
