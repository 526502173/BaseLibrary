package com.lz.baselibrary.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author linzheng
 */
open abstract class BaseViewHolder<T>(itemView:View): RecyclerView.ViewHolder(itemView){

    inline fun  getContext(): Context = itemView.context

    abstract fun bind(item: T)

}
