package com.lz.baselibrary.ui

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.model.CustomChild
import com.lz.baselibrary.model.CustomParent
import com.lz.baselibrary.ui.multitype.CustomChildItemViewBinder
import com.lz.baselibrary.ui.multitype.CustomParentItemViewBinder
import com.lz.baselibrary.view.expand.ExpandAdapter
import com.lz.baselibrary.view.itemdecoration.BaseItemDecoration
import kotlinx.android.synthetic.main.activity_expand_list.*

/**
 * @author linzheng
 */
class ExpandListActivity : LibraryBaseListActivity() {


    private val mExpandAdapter: ExpandAdapter<CustomParent> by lazy {
        ExpandAdapter<CustomParent>(adapter.items.toMutableList(), adapter)
    }

    private val mTestList = listOf(
            CustomParent("1", listOf(CustomChild("1-1"), CustomChild("1-2"), CustomChild("1-3"))),
            CustomParent("2", listOf(CustomChild("2-1"), CustomChild("2-2"), CustomChild("2-3"))),
            CustomParent("3", listOf(CustomChild("4-1"), CustomChild("3-2"), CustomChild("3-3"))),
            CustomParent("4", listOf(CustomChild("5-1"), CustomChild("4-2"), CustomChild("4-3"))),
            CustomParent("5", listOf(CustomChild("5-1"), CustomChild("5-2"), CustomChild("5-3")))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_list)


        CustomParentItemViewBinder.onParentClickListener = mExpandAdapter

        adapter.register(CustomParent::class, CustomParentItemViewBinder())
        adapter.register(CustomChild::class, CustomChildItemViewBinder())

        rv_expand_list.layoutManager = LinearLayoutManager(this)
        rv_expand_list.addItemDecoration(BaseItemDecoration.createFromBottom(2, Color.GRAY))
        rv_expand_list.adapter = adapter

        mExpandAdapter.init(mTestList)
        adapter.notifyDataSetChanged()

    }

}
