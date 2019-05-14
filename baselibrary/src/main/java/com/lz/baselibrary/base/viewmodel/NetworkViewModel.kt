package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.view.loadmore.RetryListener

/**
 * LiveDataViewModel
 * @author linzheng
 */
abstract class NetworkViewModel : ViewModel(), RetryListener {

    /**
     * 网络状态 LiveData，用在 UI 中监听网络请求的状态变化
     */
    abstract val networkStatus: LiveData<NetworkStatus>


}