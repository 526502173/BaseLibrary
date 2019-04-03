package com.lz.baselibrary.view.expand

import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
abstract class ParentItemViewBinder<T : Parent,VH : RecyclerView.ViewHolder>(val onParentClickListener: OnParentClickListener<T>) : ItemViewBinder<T, VH>()
