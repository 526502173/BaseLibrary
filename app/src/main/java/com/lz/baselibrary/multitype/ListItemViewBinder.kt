package com.lz.baselibrary.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

        private val ivImage: ImageView = itemView.iv_image

        override fun bind(item: String) {
            Glide.with(getContext())
                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524811529284&di=62b46a692992bc75b3120f3bbfe8ec88&imgtype=0&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_thumb%2F201506%2Fm2015062812514632.jpg")
                    .into(itemView.iv_image)
        }
    }

}
