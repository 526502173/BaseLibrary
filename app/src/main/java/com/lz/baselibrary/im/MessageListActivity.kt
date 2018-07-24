package com.lz.baselibrary.im

import android.os.Bundle
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.im.message.MessageListFragment

/**
 * @author linzheng
 */
class MessageListActivity : LibraryBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fl_container, MessageListFragment.newInstance())
                .commit()
    }

}
