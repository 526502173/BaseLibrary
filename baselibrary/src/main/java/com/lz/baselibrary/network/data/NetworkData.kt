package com.lz.baselibrary.network.data

import androidx.lifecycle.LiveData
import com.lz.baselibrary.network.status.NetworkStatus

/**
 * @author linzheng
 */
data class NetworkData(
        val networkStatus: LiveData<NetworkStatus>,
        val retry: () -> Unit
)