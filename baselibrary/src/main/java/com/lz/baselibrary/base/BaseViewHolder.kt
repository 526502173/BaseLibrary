package com.lz.baselibrary.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author linzheng
 */
open class BaseViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    inline fun  getContext(): Context = itemView.context

}
