package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.CustomParent
import com.lz.baselibrary.utils.expand.OnParentClickListener
import kotlinx.android.synthetic.main.item_parent.view.*

/**
 * @author linzheng
 */
class CustomParentItemViewBinder : ParentItemViewBinder<CustomParent, CustomParentItemViewBinder.ParentViewHolder>(onParentClickListener) {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ParentViewHolder(inflater.inflate(R.layout.item_parent, parent, false))

    override fun onBindViewHolder(holder: ParentViewHolder, item: CustomParent) = holder.bind(item)

    class ParentViewHolder(itemView: View) : BaseViewHolder<CustomParent>(itemView) {

        private val tvTitle: TextView = itemView.tv_title

        private val ivExpand: ImageView = itemView.iv_expand

        override fun bind(item: CustomParent) {
            tvTitle.text = item.title
            ivExpand.setOnClickListener { onParentClickListener.onParentClick(it, item) }
            ivExpand.setImageResource(if (item.isExpend) R.mipmap.expand_open else R.mipmap.expand_open)
        }

    }

    companion object {

        lateinit var onParentClickListener: OnParentClickListener<CustomParent>

    }

}
