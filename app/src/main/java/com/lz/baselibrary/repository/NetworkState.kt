package com.lz.baselibrary.repository

/**
 * @author linzheng
 */

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState private constructor(
        val status: Status,
        val msg: String? = null
) {

    companion object {
        /**
         * 加载完成状态
         */
        val LOADED = NetworkState(Status.SUCCESS)
        /**
         * 加载中状态
         */
        val LOADING = NetworkState(Status.RUNNING)

        /**
         * 加载失败状态
         */
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }

}