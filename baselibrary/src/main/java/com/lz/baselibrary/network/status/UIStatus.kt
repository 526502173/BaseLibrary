package com.lz.baselibrary.network.status

import com.billy.android.loading.Gloading

/**
 * 界面状态
 * @author linzheng
 */
enum class UIStatus {
    LOAD_MORE_NO_MORE,//无更多数据
    LOAD_MORE_NORMAL,//加载更多普通状态
    LOAD_MORE_LOADING,//加载更多加载中
    LOAD_MORE_FAIL,//加载更多失败
    REFRESH_COMPLETE,//下拉刷新完成
    REFRESHING,//开始下拉刷新
}

/**
 * 网络状态
 * [statusCode] UI 更具这个状态显示不同的样式
 */
data class NetworkStatus private constructor(
        val statusCode: Int = Gloading.STATUS_LOAD_SUCCESS
) {
    companion object {

        val LOADING by lazy { code(Gloading.STATUS_LOADING) }

        val SUCCESS by lazy { code(Gloading.STATUS_LOAD_SUCCESS) }

        val LOADED by lazy { code(1) }

        fun code(statusCode: Int) = NetworkStatus(statusCode)

    }
}

/**
 * 下拉刷新的状态
 */
data class RefreshStatus private constructor(val status: UIStatus) {
    companion object {

        val REFRESH_COMPLETE = RefreshStatus(UIStatus.REFRESH_COMPLETE)

        val REFRESHING = RefreshStatus(UIStatus.REFRESHING)

    }
}

/**
 * 加载更多状态
 */
data class LoadMoreStatus constructor(
        val status: UIStatus,
        val failCode: Int = -1
) {
    companion object {

        /**
         * 没有更多
         */
        val LOAD_MORE_NO_MORE = LoadMoreStatus(UIStatus.LOAD_MORE_NO_MORE)

        /**
         * 普通状态
         */
        val LOAD_MORE_NORMAL = LoadMoreStatus(UIStatus.LOAD_MORE_NORMAL)

        /**
         * 加载中
         */
        val LOAD_MORE_LOADING = LoadMoreStatus(UIStatus.LOAD_MORE_LOADING)

        /**
         * 加载失败
         */
        fun code(code: Int) = LoadMoreStatus(UIStatus.LOAD_MORE_FAIL, code)

    }

    override fun toString(): String {
        return when (status) {
            UIStatus.LOAD_MORE_LOADING -> "加载中..."
            UIStatus.LOAD_MORE_NORMAL -> "普通状态"
            UIStatus.LOAD_MORE_FAIL -> "失败"
            UIStatus.LOAD_MORE_NO_MORE -> "没有更多"
            else -> "未知 Status"
        }
    }
}