package com.lz.baselibrary

import android.os.Bundle
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.fragment.LazyLoadFragment
import com.lz.baselibrary.utils.LibraryFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

/**
 * @author linzheng
 */
class ViewPagerActivity : LibraryBaseActivity(){

    private val mFragmentList = listOf(
            LazyLoadFragment.newInstance("fragment 1"),
            LazyLoadFragment.newInstance("fragment 2"),
            LazyLoadFragment.newInstance("fragment 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        vp_view_pager.adapter = LibraryFragmentPagerAdapter(
                supportFragmentManager,
                mFragmentList,
                listOf("Fragment 1","Fragment 2","Fragment 3")
        )
        tl_view_pager.setupWithViewPager(vp_view_pager)
    }

}
