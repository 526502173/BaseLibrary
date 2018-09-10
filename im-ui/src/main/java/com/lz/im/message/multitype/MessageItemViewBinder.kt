package com.lz.im.message.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.im_data.Message
import com.lz.baselibrary.base.BaseViewHolder
import com.lz.im.R
import kotlinx.android.synthetic.main.item_message.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * @author linzheng
 */
abstract class MessageItemViewBinder<in VH : MessageItemViewBinder.MessageContentViewHolder> : ItemViewBinder<Message, MessageItemViewBinder.MessageViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): MessageViewHolder {
        val root = inflater.inflate(R.layout.item_message, parent, false)
        val messageContentViewHolder = onCreateMessageContentVieHolder(inflater, parent)
        return MessageViewHolder(root, messageContentViewHolder)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, item: Message) {
        holder.bind(item)
        onBindMessageContentViewHolder(holder.messageContentViewHolder as VH, item)
    }

    abstract fun onBindMessageContentViewHolder(holder: VH, item: Message)

    abstract fun onCreateMessageContentVieHolder(inflater: LayoutInflater, parent: ViewGroup): MessageContentViewHolder

    class MessageViewHolder(itemView: View, val messageContentViewHolder: MessageContentViewHolder) : BaseViewHolder<Message>(itemView) {

        val flSendContent: FrameLayout = itemView.fl_send_content

        val flReceiveContent: FrameLayout = itemView.fl_receive_content

        val ivAvatarReceive = itemView.iv_avatar_receive

        val ivAvatarSend = itemView.iv_avatar_send

        override fun bind(item: Message) {
            flReceiveContent.removeAllViews()
            flSendContent.removeAllViews()
            when (item.direct) {
                Message.Direct.RECEIVE -> {
                    flSendContent.visibility = View.GONE
                    flReceiveContent.visibility = View.VISIBLE
                    ivAvatarReceive.visibility = View.VISIBLE
                    ivAvatarSend.visibility = View.GONE
                    flReceiveContent.addView(messageContentViewHolder.itemView)
                }
                Message.Direct.SEND -> {
                    flSendContent.visibility = View.VISIBLE
                    flReceiveContent.visibility = View.GONE
                    ivAvatarReceive.visibility = View.VISIBLE
                    ivAvatarSend.visibility = View.GONE
                    flReceiveContent.addView(messageContentViewHolder.itemView)
                }
            }
        }
    }

    open class MessageContentViewHolder(itemView: View) : BaseViewHolder<Message>(itemView) {
        override fun bind(item: Message) {
        }
    }

}
