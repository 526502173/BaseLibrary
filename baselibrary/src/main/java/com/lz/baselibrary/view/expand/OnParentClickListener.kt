package com.lz.baselibrary.view.expand

import android.view.View

/**
 * @author linzheng
 */
open interface OnParentClickListener<T : Parent> {

    fun onParentClick(v: View, parent: T)

}