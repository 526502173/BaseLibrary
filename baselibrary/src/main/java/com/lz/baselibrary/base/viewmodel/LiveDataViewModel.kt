package com.lz.baselibrary.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lz.baselibrary.network.UIStatus

/**
 * LiveDataViewModel
 * @author linzheng
 */
//todo 此类用于封装非 Paging 界面的 VieModel
abstract class LiveDataViewModel : ViewModel() {

    /**
     * 网络状态 LiveData，用在 UI 中监听网络请求的状态变化
     */
    abstract val uiStatus: LiveData<UIStatus>

}