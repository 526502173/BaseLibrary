package com.lz.baselibrary.multitype

import androidx.recyclerview.widget.RecyclerView
import com.lz.baselibrary.model.expandlist.Child
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
abstract class ChildItemViewBinder<T : Child, VH : RecyclerView.ViewHolder> : ItemViewBinder<T, VH>()
