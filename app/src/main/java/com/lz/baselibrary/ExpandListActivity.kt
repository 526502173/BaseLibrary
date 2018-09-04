package com.lz.baselibrary

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.model.CustomChild
import com.lz.baselibrary.model.CustomParent
import com.lz.baselibrary.multitype.CustomChildItemViewBinder
import com.lz.baselibrary.multitype.CustomParentItemViewBinder
import com.lz.baselibrary.utils.expand.ExpandAdapter
import com.lz.baselibrary.view.VerticalItemDecoration
import kotlinx.android.synthetic.main.activity_expand_list.*
import me.drakeet.multitype.register

/**
 * @author linzheng
 */
class ExpandListActivity : LibraryBaseListActivity() {

    private val mExpandAdapter: ExpandAdapter<CustomParent> by lazy {
        ExpandAdapter<CustomParent>(mItems, mAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_list)

        val list = listOf(
                CustomParent("1", listOf(CustomChild("1-1"), CustomChild("1-2"), CustomChild("1-3"))),
                CustomParent("2", listOf(CustomChild("2-1"), CustomChild("2-2"), CustomChild("2-3"))),
                CustomParent("3", listOf(CustomChild("4-1"), CustomChild("3-2"), CustomChild("3-3"))),
                CustomParent("4", listOf(CustomChild("5-1"), CustomChild("4-2"), CustomChild("4-3"))),
                CustomParent("5", listOf(CustomChild("5-1"), CustomChild("5-2"), CustomChild("5-3")))
        )

        mAdapter.register(CustomParent::class, CustomParentItemViewBinder(mExpandAdapter))
        mAdapter.register(CustomChild::class, CustomChildItemViewBinder())

        rv_expand_list.layoutManager = LinearLayoutManager(this)
        rv_expand_list.addItemDecoration(VerticalItemDecoration(2, Color.GRAY))
        rv_expand_list.adapter = mAdapter

        mExpandAdapter.init(list)
        mAdapter.notifyDataSetChanged()

    }

}
