package com.lz.baselibrary.view.itemdecoration.loadmore

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.view.loadmore.SimpleLoadMoreViewConfig

/**
 * 实现 LoadMore 接口的自定义 Layout
 * @author linzheng
 */
class SimpleLoadMoreView(
        context: Context,
        private val mConfig: SimpleLoadMoreViewConfig = SimpleLoadMoreViewConfig.createDefault()
) : ConstraintLayout(context), LoadMore {

    /**
     * ProgressBar 对象
     */
    private val mProgressBar by lazy {
        ProgressBar(context).apply {
            id = VIEW_ID_PB_LOADING
            layoutParams = LayoutParams(
                    mConfig.progressBarWidth.dp2px(context),
                    mConfig.progressBarHeight.dp2px(context)
            ).apply {
                leftToLeft = LayoutParams.PARENT_ID
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                rightToLeft = VIEW_ID_TV_LOADING
                horizontalChainStyle = LayoutParams.CHAIN_PACKED
            }
        }
    }

    /**
     * TextView 对象
     */
    private val mTextView by lazy {
        AppCompatTextView(context).apply {
            id = VIEW_ID_TV_LOADING
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                rightToRight = LayoutParams.PARENT_ID
                leftToRight = VIEW_ID_PB_LOADING
                textSize = mConfig.textSize
                setTextColor(mConfig.textColor)
            }
            setPadding(mConfig.textPadding.dp2px(context), 0, 0, 0)
        }
    }

    override fun noMore() {
        mProgressBar.visibility = View.GONE
        mTextView.text = "加载完了..."
        mTextView.invalidate()
    }

    override fun loading() {
        mProgressBar.visibility = View.VISIBLE
        mTextView.text = "加载中..."
        mTextView.invalidate()
    }

    override fun normal() {
        //nothing
    }

    companion object {

        //TextView 的 id
        const val VIEW_ID_TV_LOADING = 2333334

        //ProgressBar 的 id
        const val VIEW_ID_PB_LOADING = 2333335

        /**
         * 创建 LoadMoreView 对象
         */
        fun create(context: Context) = SimpleLoadMoreView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, mConfig.layoutHeight.dp2px(context))
            addView(mProgressBar)
            addView(mTextView)
        }

    }


}