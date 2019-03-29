package com.lz.baselibrary.view.itemdecoration.loadmore

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lz.baselibrary.R
import com.lz.baselibrary.dp2px

/**
 * @author linzheng
 */
//todo 此 View 应该设计成可以自定义
class LoadMoreView(context: Context?) : ConstraintLayout(context), LoadMore {

    private val mProgressBar by lazy {
        ProgressBar(context).apply {
            id = R.id.pb_loading
            layoutParams = LayoutParams(20.dp2px(context!!), 20.dp2px(context!!)).apply {
                leftToLeft = LayoutParams.PARENT_ID
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                rightToLeft = R.id.tv_loading
                horizontalChainStyle = LayoutParams.CHAIN_PACKED
            }
        }
    }

    private val mTextView by lazy {
        AppCompatTextView(context).apply {
            id = R.id.tv_loading
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                rightToRight = LayoutParams.PARENT_ID
                leftToRight = R.id.pb_loading
                setTextColor(Color.BLACK)
            }
            text = "加载中..."
            setPadding(10.dp2px(context!!), 0, 0, 0)
        }
    }

    override fun noMore() {
        mProgressBar.visibility = View.GONE
        mTextView.text = "没有更多了..."
    }

    override fun loading() {
        mProgressBar.visibility = View.VISIBLE
        mTextView.text = "加载中..."
    }

    override fun normal() {
    }

    companion object {

        fun create(context: Context) = LoadMoreView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 50.dp2px(context))
            addView(mProgressBar)
            addView(mTextView)
        }

    }


}