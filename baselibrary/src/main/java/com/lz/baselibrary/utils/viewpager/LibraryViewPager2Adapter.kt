package com.lz.baselibrary.utils.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager2 的 FragmentAdapter
 * @author linzheng
 */
//todo viewpager2 还有很多问题，建议谨慎使用
class LibraryViewPager2Adapter(
        fragmentActivity: FragmentActivity,
        private val mFragmentList: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = mFragmentList.size

    override fun getItem(position: Int) = mFragmentList[position]


}