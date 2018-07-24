package com.lz.im.message.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.im_data.Message
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.im.R

import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
class UnknownTypeItemViewBinder : ItemViewBinder<Message, UnknownTypeItemViewBinder.UnknownTypeViewHolder>() {


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup)
     = UnknownTypeViewHolder(inflater.inflate(R.layout.item_message_unknown_type,parent,false))

    override fun onBindViewHolder(holder: UnknownTypeViewHolder, item: Message) {

    }

    class UnknownTypeViewHolder(itemView: View) : BaseViewHolder(itemView)

}
