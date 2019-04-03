package com.lz.baselibrary.view.expand

import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
abstract class ChildItemViewBinder<T : Child, VH : RecyclerView.ViewHolder> : ItemViewBinder<T, VH>()
