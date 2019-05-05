package com.lz.baselibrary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lz.baselibrary.R
import com.lz.baselibrary.model.wanandroid.Product
import com.lz.baselibrary.ui.multitype.ProductItemViewBinder
import com.lz.baselibrary.view.itemdecoration.stickyheader.DefaultStickyHeaderDelegate
import com.lz.baselibrary.view.itemdecoration.stickyheader.StickyHeaderItemDecoration
import kotlinx.android.synthetic.main.activity_sticky_header.*
import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
class StickyHeaderActivity : AppCompatActivity() {

    private val mAdapter = MultiTypeAdapter().apply {
        register(Product::class, ProductItemViewBinder())
    }

    private var mProductList = listOf(
            Product("辣子鸡+绿豆汤+米饭2", "折扣商品", 2),
            Product("辣子鸡+绿豆汤+米饭3", "折扣商品", 2),
            Product("辣子鸡+绿豆汤+米饭4", "折扣商品", 2),
            Product("凉拌卤藕1", "老板推荐", 1),
            Product("凉拌卤藕2", "老板推荐", 1),
            Product("凉拌卤藕3", "老板推荐", 1),
            Product("青椒鸡蛋炒火腿1", "经典家常菜", 3),
            Product("青椒鸡蛋炒火腿2", "经典家常菜", 3),
            Product("青椒鸡蛋炒火腿3", "经典家常菜", 3),
            Product("青椒鸡蛋炒火腿4", "经典家常菜", 3),
            Product("青椒鸡蛋炒火腿5", "经典家常菜", 3),
            Product("凉拌卤藕4", "老板推荐", 1),
            Product("辣子鸡+绿豆汤+米饭1", "折扣商品", 2),
            Product("辣子鸡+绿豆汤+米饭5", "折扣商品", 2),
            Product("凉拌卤藕5", "老板推荐", 1),
            Product("凉拌卤藕6", "老板推荐", 1),
            Product("凉拌卤藕7", "老板推荐", 1),
            Product("凉拌卤藕8", "老板推荐", 1),
            Product("辣子鸡+绿豆汤+米饭6", "折扣商品", 2),
            Product("辣子鸡+绿豆汤+米饭7", "折扣商品", 2),
            Product("辣子鸡+绿豆汤+米饭8", "折扣商品", 2),
            Product("青椒鸡蛋炒火腿6", "经典家常菜", 3),
            Product("青椒鸡蛋炒火腿7", "经典家常菜", 3),
            Product("番茄鸡蛋汤3", "汤品小吃", 4),
            Product("番茄鸡蛋汤4", "汤品小吃", 4),
            Product("番茄鸡蛋汤5", "汤品小吃", 4),
            Product("番茄鸡蛋汤6", "汤品小吃", 4),
            Product("番茄鸡蛋汤7", "汤品小吃", 4),
            Product("青椒鸡蛋炒火腿8", "经典家常菜", 3),
            Product("番茄鸡蛋汤1", "汤品小吃", 4),
            Product("番茄鸡蛋汤2", "汤品小吃", 4),
            Product("番茄鸡蛋汤8", "汤品小吃", 4)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky_header)
        val items = mProductList.sortedBy { it.stickySortValue }
        mAdapter.items = items
        rv_sticky_header.layoutManager = LinearLayoutManager(this)
        rv_sticky_header.addItemDecoration(StickyHeaderItemDecoration(delegate = DefaultStickyHeaderDelegate(items)))
        rv_sticky_header.adapter = mAdapter

    }

}