package com.lz.im.message.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.im_data.Message
import com.lz.im.R
import kotlinx.android.synthetic.main.item_message_text.view.*

/**
 * @author linzheng
 */
class TextMessageItemViewBinder : MessageItemViewBinder<TextMessageItemViewBinder.TextMessageContentViewHolder>() {
    override fun onBindMessageContentViewHolder(holder: TextMessageContentViewHolder, item: Message) {
        holder.itemView.apply {
            tv_text.text = item.content
        }
    }

    override fun onCreateMessageContentVieHolder(inflater: LayoutInflater, parent: ViewGroup) = TextMessageContentViewHolder(inflater.inflate(R.layout.item_message_text, parent, false))

    class TextMessageContentViewHolder(itemView: View) : MessageItemViewBinder.MessageContentViewHolder(itemView)

}
