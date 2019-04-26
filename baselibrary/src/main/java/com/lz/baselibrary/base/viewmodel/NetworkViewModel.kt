package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lz.baselibrary.network.status.NetworkStatus

/**
 * LiveDataViewModel
 * @author linzheng
 */
abstract class NetworkViewModel : ViewModel() {

    /**
     * 网络状态 LiveData，用在 UI 中监听网络请求的状态变化
     */
    abstract val networkStatus: LiveData<NetworkStatus>

    /**
     * 重试
     */
    abstract fun retry()

}