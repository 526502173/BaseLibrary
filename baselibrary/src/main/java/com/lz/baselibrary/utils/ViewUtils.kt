package com.lz.baselibrary.utils

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import com.google.android.material.tabs.TabLayout
import com.lz.baselibrary.dp

/**
 * @author linzheng
 */
object ViewUtils {

    /**
     * 使用反射修改 TabLayout 指示器的线条长度
     * 该方法有一个弊端，就是线条长度最小不能小于
     * 指示器文章的长度
     */
    fun refexSetTabLayoutLineWidth(tabLayout: TabLayout, width: Int) {
        tabLayout.post {
            val tabStrip = tabLayout.getChildAt(0) as ViewGroup
            tabStrip.forEach {
                val textViewField = it::class.java.getDeclaredField("mTextView")
                textViewField.isAccessible = true
                val textView = textViewField.get(it) as TextView
                it.setPadding(0, 0, 0, 0)
                val measureWidth = if (textView.width != 0) textView.width else {
                    textView.measure(0, 0)
                    textView.width
                }
                val params = (it.layoutParams as LinearLayout.LayoutParams).apply {
                    this.width = measureWidth
                    this.leftMargin = width.dp(tabLayout.context)
                    this.rightMargin = width.dp(tabLayout.context)
                }
                it.layoutParams = params
                it.invalidate()
            }
        }
    }

}