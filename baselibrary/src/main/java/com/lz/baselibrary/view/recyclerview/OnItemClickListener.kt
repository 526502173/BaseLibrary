package com.lz.baselibrary.view.recyclerview

import android.view.View

/**
 * @author linzheng
 */
interface OnItemClickListener {

    fun onItemClick(view: View, position: Int)

    fun onItemLongClick(view: View, position: Int)

}