package com.lz.baselibrary.network

/**
 * 界面状态
 * @author linzheng
 */
enum class UIStatus {
    LOAD_MORE_NO_MORE,//无更多数据
    LOAD_MORE_NORMAL,//加载更多普通状态
    LOAD_MORE_LOADING,//加载更多加载中
    REFRESH_COMPLETE,//下拉刷新完成
    REFRESHING,//开始下拉刷新
    LOADING, //加载中
    FAILED, //加载失败
    SUCCESS //加载成功
}

/**
 * 网络状态
 * [status] UI 更具这个状态显示不同的样式
 * [failCode] 如果状态是 failed 则需要制定具体的错误类型
 */
data class NetworkStatus private constructor(
        val status: UIStatus,
        val failCode: Int = 0
) {
    companion object {

        val LOADING by lazy { NetworkStatus(UIStatus.LOADING) }

        val SUCCESS by lazy { NetworkStatus(UIStatus.SUCCESS) }

        fun fail(failCode: Int) = NetworkStatus(UIStatus.FAILED, failCode)

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
data class LoadMoreStatus private constructor(val status: UIStatus) {
    companion object {

        val LOAD_MORE_NO_MORE = LoadMoreStatus(UIStatus.LOAD_MORE_NO_MORE)

        val LOAD_MORE_NORMAL = LoadMoreStatus(UIStatus.LOAD_MORE_NORMAL)

        val LOAD_MORE_LOADING = LoadMoreStatus(UIStatus.LOAD_MORE_LOADING)

    }
}