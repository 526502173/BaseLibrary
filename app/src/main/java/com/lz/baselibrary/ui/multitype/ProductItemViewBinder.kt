package com.lz.baselibrary.ui.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.R
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.baselibrary.model.wanandroid.Product
import kotlinx.android.synthetic.main.item_product.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class ProductItemViewBinder : ItemViewBinder<Product, ProductItemViewBinder.ProductViewHolder>() {

    override fun onBindViewHolder(holder: ProductViewHolder, item: Product) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ProductViewHolder {
        return ProductViewHolder(inflater.inflate(R.layout.item_product, parent, false))
    }

    class ProductViewHolder(view: View) : BaseViewHolder<Product>(view) {
        override fun bind(item: Product) {
            itemView.tv_name.text = item.name
        }
    }

}