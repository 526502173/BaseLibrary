package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_list.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/26
 * desc   : ListItemViewBinder
 * version: 1.0
</pre> *
 */
class ListItemViewBinder : ItemViewBinder<String, ListItemViewBinder.ListViewHolder>() {
    
    override fun onBindViewHolder(holder: ListViewHolder, item: String) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ListViewHolder = ListViewHolder(inflater.inflate(R.layout.item_list, parent, false))

    class ListViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {

        override fun bind(item: String) {
            Glide.with(getContext())
                    .load("https://s2.ax1x.com/2019/02/28/k70kjg.png")
                    .into(itemView.iv_image)
        }
    }

}
