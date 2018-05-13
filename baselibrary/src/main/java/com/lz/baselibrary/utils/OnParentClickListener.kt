package com.lz.baselibrary.utils

import android.view.View
import com.lz.baselibrary.model.expandlist.Parent

/**
 * @author linzheng
 */
open interface OnParentClickListener<T : Parent> {

    fun onParentClick(v: View, parent: T)

}