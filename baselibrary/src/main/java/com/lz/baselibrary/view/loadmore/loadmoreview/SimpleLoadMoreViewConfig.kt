package com.lz.baselibrary.view.loadmore

import android.graphics.Color

/**
 * [LoadMoreView] 的一些配置
 * 下面所有长度单位都是 dp 字体大小单位为 sp
 * @author linzheng
 */
data class SimpleLoadMoreViewConfig(
        val loadingText: String = "加载中...",
        val noMoreText: String = "没有更多了...",
        val textSize: Float = 12f,
        val textColor: Int = Color.BLACK,
        val textPadding: Int = 10,
        val progressBarWidth: Int = 20,
        val progressBarHeight: Int = 20,
        val layoutHeight: Int = 50
) {
    companion object {

        /**
         * 创建默认 [SimpleLoadMoreViewConfig] 对象
         */
        fun createDefault() = SimpleLoadMoreViewConfig()

    }
}