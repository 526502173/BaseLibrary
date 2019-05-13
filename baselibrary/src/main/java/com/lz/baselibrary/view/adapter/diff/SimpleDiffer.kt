package com.lz.baselibrary.view.adapter.diff

import me.drakeet.multitype.MultiTypeAdapter

/**
 * @author linzheng
 */
abstract class SimpleDiffer<T> : Differ<T> {

    override lateinit var adapter: MultiTypeAdapter

    override fun getItem(position: Int): Any? {
        return currentList[position]
    }

    override fun getItemCount() = currentList.size

}