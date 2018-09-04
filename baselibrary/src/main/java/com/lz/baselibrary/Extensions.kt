package com.lz.baselibrary

import me.drakeet.multitype.Items

/**
 * @author linzheng
 */

/**
 * 计算 dp 值
 */
inline fun Int.dp(): Int = (LibraryApplication.getInstance().resources.displayMetrics.density * this).toInt()

/**
 * 给 MultiType 中的 Items 设计的方法
 * 用于获取 Items 中某类的 Item
 * 类型通过泛型 T 指定
 */
inline fun <reified T> Items.getTypeItems(items: Items) = items.filter { it is T }.map { it as T }

/**
 * 此方法作用和上面的方法一样
 * 不过性能更好
 * 在确定了 Items 中所有的 Item 都是一种类型的时候
 * 可以使用该方法
 */
inline fun <reified T> Items.quickGetTypeItems(items: Items) = items.map { it as T }
