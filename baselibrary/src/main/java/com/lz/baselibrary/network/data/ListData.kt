package com.lz.baselibrary.network.data

import androidx.lifecycle.MutableLiveData
import com.lz.baselibrary.network.status.UIStatusData

/**
 * @author linzheng
 */
data class ListData private constructor(
        val list: MutableLiveData<List<Any>>,
        val uiStatus: UIStatusData,
        val refresh: () -> Unit,
        val retry: () -> Unit
) {

    companion object {
        fun create(
                list: MutableLiveData<List<Any>> = MutableLiveData(),
                uiStatus: UIStatusData,
                refresh: () -> Unit = {},
                retry: () -> Unit = {}
        ) = ListData(
                list, uiStatus, refresh, retry
        )

        fun createLiveData(
                list: MutableLiveData<List<Any>> = MutableLiveData(),
                refresh: () -> Unit = {},
                retry: () -> Unit = {}
        ) = MutableLiveData<ListData>().apply {
            value = create(list, UIStatusData.create(), refresh, retry)
        }

    }

}