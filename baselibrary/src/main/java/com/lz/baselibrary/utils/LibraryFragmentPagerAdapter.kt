package com.lz.baselibrary.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author linzheng
 */
class LibraryFragmentPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragmentList:List<Fragment>,
        private val mPageTitles:List<String> = listOf()
) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return if(!mPageTitles.isEmpty())mPageTitles[position] else null
    }
}