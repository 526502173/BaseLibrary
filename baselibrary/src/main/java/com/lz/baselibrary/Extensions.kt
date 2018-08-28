package com.lz.baselibrary

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.res.ResourcesCompat

/**
 * @author linzheng
 */

/**
 * dp
 */
inline fun Int.dp(): Int = (LibraryApplication.getInstance().resources.displayMetrics.density * this).toInt()

/**
 * 在 Activity 中获取 color
 *
 */
inline fun Context.getCompatColor(@ColorRes id: Int) = ResourcesCompat.getColor(resources, id, theme)
