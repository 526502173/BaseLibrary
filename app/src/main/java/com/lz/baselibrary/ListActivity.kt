package com.lz.baselibrary

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.multitype.ListItemViewBinder
import com.lz.baselibrary.utils.ToastUtils
import com.lz.baselibrary.view.RefreshListener
import kotlinx.android.synthetic.main.activity_list.*
import me.drakeet.multitype.register

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/04/26
 * desc   : ListActivity
 * version: 1.0
</pre> *
 */
class ListActivity : LibraryBaseListActivity(),RefreshListener {

    private val mHandler:Handler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            ToastUtils.showToast("刷新成功！")
            srl_list.refreshComplete()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        mAdapter.register(String::class, ListItemViewBinder())
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = mAdapter

        srl_list.setRefreshListener(this)

        repeat(10) {
            mItems.add("123")
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun refresh(isRefresh: Boolean) {
        mHandler.sendEmptyMessageDelayed(1,3000)
    }

}
