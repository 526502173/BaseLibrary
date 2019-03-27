package com.lz.baselibrary.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author linzheng
 */
open class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * 获取 ItemView 的 context 对象
     */
    inline fun getContext(): Context = itemView.context

    /**
     * 讲数据绑定到 ViewHolder 上
     * 这个方法配合 DiffUtils 一起使用
     */
    open fun bind(item: T) {}

}
