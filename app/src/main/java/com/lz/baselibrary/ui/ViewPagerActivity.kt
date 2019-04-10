package com.lz.baselibrary.ui

import android.os.Bundle
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.ui.fragment.LazyLoadFragment
import com.lz.baselibrary.utils.viewpager.LibraryViewPager2Adapter
import com.lz.baselibrary.view.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_view_pager.*

/**
 * ViewPager2 + TabLayout
 * @author linzheng
 */
class ViewPagerActivity : LibraryBaseActivity() {

    private val mFragmentList = listOf(
            LazyLoadFragment.newInstance("fragment 1"),
            LazyLoadFragment.newInstance("fragment 2"),
            LazyLoadFragment.newInstance("fragment 3"),
            LazyLoadFragment.newInstance("fragment 4"),
            LazyLoadFragment.newInstance("fragment 5"),
            LazyLoadFragment.newInstance("fragment 6"),
            LazyLoadFragment.newInstance("fragment 7"),
            LazyLoadFragment.newInstance("fragment 8")
    )

    private val mTitleList = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        viewpager.adapter = LibraryViewPager2Adapter(this,mFragmentList)
        TabLayoutMediator(tl_view_pager, viewpager) { tab, position ->
            tab.text = mTitleList[position]
        }.attach()

    }

}
