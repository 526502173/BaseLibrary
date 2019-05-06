package com.lz.baselibrary.view.adapter.diff

/**
 * @author linzheng
 */
abstract class SimpleDiffer<T> : Differ<T> {

    override fun getItem(position: Int): Any? {
        return currentList[position]
    }

    override fun getItemCount() = currentList.size

}