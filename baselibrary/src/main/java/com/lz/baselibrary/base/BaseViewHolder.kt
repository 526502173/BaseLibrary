package com.lz.baselibrary.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author linzheng
 */
open abstract class BaseViewHolder<T>(itemView:View): RecyclerView.ViewHolder(itemView){

    inline fun getContext(): Context = itemView.context

    abstract fun bind(item: T)

}
