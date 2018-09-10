package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.CustomChild
import kotlinx.android.synthetic.main.item_child.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class CustomChildItemViewBinder : ItemViewBinder<CustomChild, CustomChildItemViewBinder.ChildViewHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ChildViewHolder(inflater.inflate(R.layout.item_child, parent, false))

    override fun onBindViewHolder(holder: ChildViewHolder, item: CustomChild) = holder.bind(item)

    class ChildViewHolder(itemView: View) : BaseViewHolder<CustomChild>(itemView) {

        private val tvTitle: TextView = itemView.tv_title

        override fun bind(item: CustomChild) {
            tvTitle.text = item.title
        }
    }

}
