package com.lz.im.message.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.im_data.Message

import com.lz.baselibrary.base.BaseViewHolder
import com.lz.im.R
import kotlinx.android.synthetic.main.item_message.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
abstract class MessageItemViewBinder<in VH: MessageItemViewBinder.MessageContentViewHolder> : ItemViewBinder<Message, MessageItemViewBinder.MessageViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MessageViewHolder {
        val root = inflater.inflate(R.layout.item_message,parent,false)
        val messageContentViewHolder = onCreateMessageContentVieHolder(inflater,parent)
        return MessageViewHolder(root,messageContentViewHolder)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, item: Message) {
        onBindMessageContentViewHolder(holder.messageContentViewHolder as VH,item)
        holder.itemView.apply {
            fl_receive_content.removeAllViews()
            fl_send_content.removeAllViews()
            when(item.direct){
                Message.Direct.RECEIVE -> {
                    fl_send_content.visibility = View.GONE
                    fl_receive_content.visibility = View.VISIBLE
                    iv_avatar_receive.visibility = View.VISIBLE
                    iv_avatar_send.visibility = View.GONE
                    fl_receive_content.addView(holder.messageContentViewHolder.itemView)
                }
                Message.Direct.SEND -> {
                    fl_send_content.visibility = View.VISIBLE
                    fl_receive_content.visibility = View.GONE
                    iv_avatar_send.visibility = View.VISIBLE
                    iv_avatar_receive.visibility = View.GONE
                    fl_send_content.addView(holder.messageContentViewHolder.itemView)
                }
            }
        }
    }

    abstract fun onBindMessageContentViewHolder(holder: VH, item: Message)

    abstract fun onCreateMessageContentVieHolder(inflater: LayoutInflater, parent: ViewGroup):MessageContentViewHolder

    class MessageViewHolder(itemView: View, val messageContentViewHolder: MessageContentViewHolder) : BaseViewHolder(itemView)

    open class MessageContentViewHolder(itemView: View) : BaseViewHolder(itemView)

}
