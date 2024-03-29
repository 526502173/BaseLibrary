package com.lz.baselibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.lz.baselibrary.R
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.ui.multitype.ArticleItemViewBinder


/**
 * @author linzheng
 */
class ArticleListAdapter : PagedListAdapter<Article, ArticleItemViewBinder.ArticleViewHolder>(MyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewBinder.ArticleViewHolder {
        return ArticleItemViewBinder.ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_list
    }

    override fun onBindViewHolder(holder: ArticleItemViewBinder.ArticleViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return when {
                oldItem.title != newItem.title -> false
                oldItem.publishTime != newItem.publishTime -> false
                else -> true
            }
        }

    }

}