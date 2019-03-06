package com.lz.im.message

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.im_data.Message
import com.example.im_data.MessageFactory
import com.lz.baselibrary.base.LibraryBaseListFragment
import com.lz.im.R
import com.lz.im.message.multitype.ImageMessageItemViewBinder
import com.lz.im.message.multitype.TextMessageItemViewBinder
import com.lz.im.message.multitype.UnknownTypeItemViewBinder
import kotlinx.android.synthetic.main.fragment_message_list.*
import me.drakeet.multitype.register

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/03/22
 * desc   : 消息列表
 * version: 1.0
</pre> *
 */
class MessageListFragment : LibraryBaseListFragment() {

    override fun loadData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapter.apply {
            register(Message::class)
                    .to(TextMessageItemViewBinder(), ImageMessageItemViewBinder(), UnknownTypeItemViewBinder())
                    .withClassLinker { _, t ->
                        when (t.type) {
                            Message.Type.TEXT -> TextMessageItemViewBinder::class.java
                            Message.Type.IMAGE -> ImageMessageItemViewBinder::class.java
                            else -> UnknownTypeItemViewBinder::class.java
                        }
                    }
        }
        rv_message_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        rv_message_list.adapter = mAdapter
        btn_send.setOnClickListener {
            mItems.add(MessageFactory.createTextMessage(et_input_box.text.trim().toString()))
            mAdapter.notifyItemInserted(mItems.size)
        }
    }

    companion object {
        fun newInstance(): MessageListFragment {
            val args = Bundle()
            val fragment = MessageListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
