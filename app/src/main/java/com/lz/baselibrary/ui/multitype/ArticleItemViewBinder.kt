package com.lz.baselibrary.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.wanandroid.Article
import com.lz.baselibrary.view.loadmore.paging.DiffItemViewBinder
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * @author linzheng
 */
class ArticleItemViewBinder : DiffItemViewBinder<Article, ArticleItemViewBinder.ArticleViewHolder>() {

    override fun onBindViewHolder(holder: ArticleViewHolder, item: Article) = holder.bind(item)

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) = ArticleViewHolder(inflater.inflate(R.layout.item_list, parent, false))


    class ArticleViewHolder(view: View) : BaseViewHolder<Article>(view) {
        override fun bind(item: Article) {
            itemView.tv_title.text = item.title.parseAsHtml()
            itemView.tv_publish_date.text = item.niceDate
        }
    }

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return when {
            oldItem.title != newItem.title -> false
            oldItem.publishTime != newItem.publishTime -> false
            else -> true
        }
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

}