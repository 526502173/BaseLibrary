package com.lz.baselibrary.multitype

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.wanandroid.SubscriptionArticle
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
class SubscriptionArticleItemViewBinder : ItemViewBinder<SubscriptionArticle, SubscriptionArticleItemViewBinder.ListViewHolder>() {

    override fun onBindViewHolder(holder: ListViewHolder, item: SubscriptionArticle) =
            holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ListViewHolder =
            ListViewHolder(inflater.inflate(R.layout.item_list, parent, false))

    class ListViewHolder(itemView: View) : BaseViewHolder<SubscriptionArticle>(itemView) {

        override fun bind(item: SubscriptionArticle) {
            itemView.tv_title.text = item.title.parseAsHtml()
            itemView.tv_publish_date.text = item.niceDate
        }
    }

}
