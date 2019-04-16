package com.lz.baselibrary.view.loadmore.paging

import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder

/**
 * DiffItemViewBinder 继承 MultiType 的 ItemViewBinder
 * 并实现了 DiffItemCallback 接口，在原本的基础上实现了 DiffUtil 的 diff 功能
 * @author linzheng
 */
abstract class DiffItemViewBinder<T, VH : RecyclerView.ViewHolder> : ItemViewBinder<T, VH>(), DiffItemCallback<T> {

    override fun getChangePayload(oldItem: T, newItem: T): Any? = null

}