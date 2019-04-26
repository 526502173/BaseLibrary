package com.lz.baselibrary.view.itemdecoration.loadmore

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.lz.baselibrary.dp2px
import com.lz.baselibrary.utils.initializer.LibraryLoadMoreInitialize

/**
 * 实现 LoadMore 接口的自定义 Layout
 * @constructor context 必须是 Activity 类型
 * @author linzheng
 */
class SimpleLoadMoreView(
        context: Context,
        private val mRetry: () -> Unit
) : ConstraintLayout(context), LoadMore {

    /**
     * 配置对象
     */
    private val mConfig = LibraryLoadMoreInitialize.sSimpleLoadMoreViewConfig

    init {
        //防止事件传递到 RecyclerView 的 onItemTouchEvent 中从而导致索引异常
        isClickable = true
    }

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
                rightToLeft = VIEW_ID_BTN_RETRY
                leftToRight = VIEW_ID_PB_LOADING
                textSize = mConfig.textSize
                setTextColor(mConfig.textColor)
            }
            setPadding(mConfig.textPadding.dp2px(context), 0, 0, 0)
        }
    }

    /**
     * 重试 Button
     */
    private val mRetryButton by lazy {
        AppCompatButton(context).apply {
            id = VIEW_ID_BTN_RETRY
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                topToTop = LayoutParams.PARENT_ID
                bottomToBottom = LayoutParams.PARENT_ID
                rightToRight = LayoutParams.PARENT_ID
                leftToRight = VIEW_ID_TV_LOADING
                textSize = mConfig.textSize
            }
            text = "点我点我"
            setOnClickListener {
                mRetry.invoke()
            }
        }
    }

    override fun noMore() {
        resetAllView()
        mTextView.text = mConfig.noMoreText
    }

    override fun loading() {
        resetAllView()
        mProgressBar.visibility = View.VISIBLE
        mTextView.text = mConfig.loadingText
    }

    override fun fail(code: Int) {
        resetAllView()
        mTextView.text = when (code) {
            LOAD_MORE_FAIL_CODE_NOT_NETWORK -> "没有网络了..."
            LOAD_MORE_FAIL_CODE_HTTP -> "HTTP 异常..."
            else -> "未知的异常..."
        }
        mRetryButton.visibility = View.VISIBLE
    }

    override fun normal() {
        //ignore
    }

    private fun resetAllView() {
        mProgressBar.visibility = View.GONE
        mRetryButton.visibility = View.GONE
    }

    companion object {

        //TextView 的 id
        const val VIEW_ID_TV_LOADING = 2333334

        //ProgressBar 的 id
        const val VIEW_ID_PB_LOADING = 2333335

        //Button 的 id
        const val VIEW_ID_BTN_RETRY = 2333336

        /**
         * 创建 LoadMoreView 对象
         */
        fun create(
                context: Context,
                retry: () -> Unit
        ) = SimpleLoadMoreView(context, retry).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, mConfig.layoutHeight.dp2px(context))
            addView(mProgressBar)
            addView(mTextView)
            addView(mRetryButton)
        }

    }


}