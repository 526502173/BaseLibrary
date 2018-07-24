package com.lz.im.message.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.im_data.Message
import com.lz.im.R

/**
 * @author linzheng
 */
class ImageMessageItemViewBinder:MessageItemViewBinder<ImageMessageItemViewBinder.ImageMessageViewHolder>(){
    override fun onBindMessageContentViewHolder(holder: ImageMessageViewHolder, item: Message) {
        holder.itemView.apply {

        }
    }

    override fun onCreateMessageContentVieHolder(inflater: LayoutInflater, parent: ViewGroup)
     = ImageMessageViewHolder(inflater.inflate(R.layout.item_message_image,parent,false))


    class ImageMessageViewHolder(itemView:View) : MessageItemViewBinder.MessageContentViewHolder(itemView) {}

}
