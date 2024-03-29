package com.lz.baselibrary.base.viewmodel.delegate

import androidx.lifecycle.LiveData
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus

/**
 * @author linzheng
 */
interface ListViewModelDelegate {

    val networkStatus: LiveData<NetworkStatus>

    val refreshStatus: LiveData<RefreshStatus>

    val loadMoreStatus: LiveData<LoadMoreStatus>

}