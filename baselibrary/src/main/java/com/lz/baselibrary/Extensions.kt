package com.lz.baselibrary

import android.content.Context
import android.view.View
import androidx.paging.PagedList

/**
 * 扩展方法
 * @author linzheng
 */

/**
 * dp 2 px
 */
inline fun Int.dp2px(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

inline fun Float.dp2px(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

inline fun Double.dp2px(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

/**
 * 通过 Boolean类型的值来设置 View 的 Visibility
 * true 表示 [View.VISIBLE]
 * false 表示 [View.GONE]
 */
inline fun View.setVisibility(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.GONE
}

/**
 * 将 PagedList<T> 类的对象转换成 PagedList<Any> 类型的对象
 * 理论上讲这里是不会有问题的
 */
inline fun <T> PagedList<T>.toAnyType(): PagedList<Any> {
    return this as PagedList<Any>
}