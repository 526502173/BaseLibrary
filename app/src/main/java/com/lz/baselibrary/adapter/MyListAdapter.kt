package com.lz.baselibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lz.baselibrary.R
import com.lz.baselibrary.model.wanandroid.SubscriptionArticle
import com.lz.baselibrary.multitype.SubscriptionArticleItemViewBinder


/**
 * @author linzheng
 */
class MyListAdapter : ListAdapter<SubscriptionArticle, SubscriptionArticleItemViewBinder.SubscriptionArticleHolder>(MyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionArticleItemViewBinder.SubscriptionArticleHolder {
        return SubscriptionArticleItemViewBinder.SubscriptionArticleHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: SubscriptionArticleItemViewBinder.SubscriptionArticleHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyDiffCallback : DiffUtil.ItemCallback<SubscriptionArticle>() {
        override fun areItemsTheSame(oldItem: SubscriptionArticle, newItem: SubscriptionArticle) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SubscriptionArticle, newItem: SubscriptionArticle) = false

    }

}