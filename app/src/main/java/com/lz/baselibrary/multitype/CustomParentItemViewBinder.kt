package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.CustomParent
import com.lz.baselibrary.utils.OnParentClickListener

/**
 * @author linzheng
 */
class CustomParentItemViewBinder(onParentClickListener: OnParentClickListener<CustomParent>):ParentItemViewBinder<CustomParent,CustomParentItemViewBinder.ParentViewHolder>(onParentClickListener){
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = ParentViewHolder(inflater.inflate(R.layout.item_parent,parent,false))

    override fun onBindViewHolder(holder: ParentViewHolder, item: CustomParent) {
        holder.itemView.setOnClickListener {
            onParentClickListener.onParentClick(it,item)
        }
    }

    class ParentViewHolder(itemView : View) : BaseViewHolder(itemView)

}
