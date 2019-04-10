package com.lz.baselibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.lz.baselibrary.R
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.SubscriptionArticleItemViewBinder


/**
 * @author linzheng
 */
class ArticleListAdapter : PagedListAdapter<Article, SubscriptionArticleItemViewBinder.SubscriptionArticleHolder>(MyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionArticleItemViewBinder.SubscriptionArticleHolder {
        return SubscriptionArticleItemViewBinder.SubscriptionArticleHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: SubscriptionArticleItemViewBinder.SubscriptionArticleHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Article, newItem: Article) = false

    }

}