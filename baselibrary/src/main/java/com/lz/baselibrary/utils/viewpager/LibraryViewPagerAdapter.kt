package com.lz.baselibrary.utils.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * ViewPager 的 Fragment
 * @author linzheng
 */
class LibraryViewPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragmentList: List<Fragment>,
        private val mPageTitles: List<String> = listOf()
) : androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = if (mPageTitles.isNotEmpty()) mPageTitles[position] else ""
}