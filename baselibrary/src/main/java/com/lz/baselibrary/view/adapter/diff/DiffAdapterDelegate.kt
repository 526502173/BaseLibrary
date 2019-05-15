package com.lz.baselibrary.view.adapter.diff

/**
 * @author linzheng
 */
interface DiffAdapterDelegate {

    var items: List<Any>

    fun getItemCount(): Int

}