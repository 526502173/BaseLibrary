package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.CustomChild
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class CustomChildItemViewBinder : ItemViewBinder<CustomChild, CustomChildItemViewBinder.ChildViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = ChildViewHolder(inflater.inflate(R.layout.item_child,parent,false))

    override fun onBindViewHolder(holder: ChildViewHolder, item: CustomChild) {
    }

    class ChildViewHolder(itemView : View) : BaseViewHolder(itemView)

}
