package com.lz.baselibrary.view.adapter.diff

/**
 * @author linzheng
 */
interface Differ<T> {

    val currentList: List<Any>

    fun getItem(position: Int): Any?

    fun getItemCount(): Int

    fun submitList(newList: T)

}