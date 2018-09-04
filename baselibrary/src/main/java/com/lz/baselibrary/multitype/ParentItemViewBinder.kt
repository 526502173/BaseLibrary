package com.lz.baselibrary.multitype

import android.support.v7.widget.RecyclerView
import com.lz.baselibrary.model.expandlist.Parent
import com.lz.baselibrary.utils.expand.OnParentClickListener
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
abstract class ParentItemViewBinder<T : Parent,VH : RecyclerView.ViewHolder?>(val onParentClickListener: OnParentClickListener<T>) : ItemViewBinder<T, VH>()
