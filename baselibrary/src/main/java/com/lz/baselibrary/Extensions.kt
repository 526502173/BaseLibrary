package com.lz.baselibrary

import android.content.Context
import android.view.View

/**
 * 扩展方法
 * @author linzheng
 */

/**
 * 计算 dp 值
 */
inline fun Int.dp(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

/**
 * 通过 Boolean类型的值来设置 View 的 Visibility
 * true 表示 [View.VISIBLE]
 * false 表示 [View.GONE]
 */
inline fun View.setViewVisibilityByBoolean(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.GONE
}
