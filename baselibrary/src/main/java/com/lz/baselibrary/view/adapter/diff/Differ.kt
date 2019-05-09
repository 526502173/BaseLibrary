package com.lz.baselibrary.view.adapter.diff

/**
 * 对 AsyncListDiffer 和 AsyncPagedListDiffer 的抽象
 * @author linzheng
 */
interface Differ<T> {

    val currentList: List<Any>

    fun getItem(position: Int): Any?

    fun getItemCount(): Int

    fun submitList(newList: T)

    fun submitList(newList: T, callback: Runnable)

}