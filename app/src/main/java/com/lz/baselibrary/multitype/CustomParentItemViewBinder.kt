package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.CustomParent
import com.lz.baselibrary.utils.expand.OnParentClickListener
import kotlinx.android.synthetic.main.item_parent.view.*

/**
 * @author linzheng
 */
class CustomParentItemViewBinder(onParentClickListener: OnParentClickListener<CustomParent>) : ParentItemViewBinder<CustomParent, CustomParentItemViewBinder.ParentViewHolder>(onParentClickListener) {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ParentViewHolder(inflater.inflate(R.layout.item_parent, parent, false))

    override fun onBindViewHolder(holder: ParentViewHolder, item: CustomParent) {
        holder.itemView.apply {
            tv_title.text = item.title
            iv_expand.setOnClickListener { onParentClickListener.onParentClick(it, item) }
            iv_expand.setImageResource(if (item.isExpend) R.mipmap.expand_close else R.mipmap.expand_open)
        }
    }

    class ParentViewHolder(itemView: View) : BaseViewHolder(itemView)

}
