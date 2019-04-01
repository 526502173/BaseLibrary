package com.lz.baselibrary.view.refresh

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * 自定义 [SwipeRefreshLayout]
 * 实现 [Refresh] 接口
 * @author linzheng
 */
class RefreshLayout(context: Context, attrs: AttributeSet?) : SwipeRefreshLayout(context, attrs), Refresh {

    override fun refreshing() {
        isRefreshing = true
    }

    override fun complete() {
        isRefreshing = false
    }

}