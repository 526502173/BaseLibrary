package com.lz.baselibrary

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.billy.android.loading.Gloading
import com.lz.baselibrary.base.LibraryBaseActivity
import com.lz.baselibrary.base.LibraryBaseListActivity
import com.lz.baselibrary.base.viewmodel.CommonListViewModel
import com.lz.baselibrary.network.status.LoadMoreStatus
import com.lz.baselibrary.network.status.NetworkStatus
import com.lz.baselibrary.network.status.RefreshStatus
import com.lz.baselibrary.view.adapter.loadmore.LoadMoreAdapter
import com.lz.baselibrary.view.adapter.submit.SubmitDelegate

/**
 * 扩展方法
 * @author linzheng
 */

/**
 * dp 2 px
 */
inline fun Int.dp2px(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

inline fun Float.dp2px(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

inline fun Double.dp2px(app: Context): Int = (app.resources.displayMetrics.density * this).toInt()

/**
 * 通过 Boolean类型的值来设置 View 的 Visibility
 * true 表示 [View.VISIBLE]
 * false 表示 [View.GONE]
 */
inline fun View.setVisibility(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.GONE
}

/**
 * 将 PagedList<T> 类的对象转换成 PagedList<Any> 类型的对象
 * 理论上讲这里是不会有问题的
 */
inline fun <T> PagedList<T>.toAnyType(): PagedList<Any> {
    return this as PagedList<Any>
}

inline fun <reified T> List<Any>.getTypeList() = filter { it is T }.map { it as T }

inline fun <reified T> List<Any>.getTypeListQuick() = map { it as T }


/**
 * 绑定 [NetworkStatus],[RefreshStatus] 和 [LoadMoreStatus]
 */
inline fun CommonListViewModel.bindUIStatus(baseActivity: LibraryBaseListActivity) {
    networkStatus.bindNetworkStatus(baseActivity)
    loadMoreStatus.bindLoadMoreStatus(baseActivity)
    refreshStatus.bindRefreshStatus(baseActivity)
}

/**
 * 绑定 [RefreshStatus]
 */
inline fun LiveData<RefreshStatus>.bindRefreshStatus(baseActivity: LibraryBaseListActivity) {
    observe(baseActivity, Observer {
        when (it) {
            RefreshStatus.REFRESH_COMPLETE -> baseActivity.refreshComplete()
            RefreshStatus.REFRESHING -> baseActivity.refreshing()
        }
    })
}

/**
 * 绑定 [NetworkStatus]
 */
inline fun LiveData<NetworkStatus>.bindNetworkStatus(baseActivity: LibraryBaseActivity) {
    observe(baseActivity, Observer {
        when (it) {
            NetworkStatus.LOADING -> baseActivity.showLoading()
            NetworkStatus.SUCCESS -> baseActivity.showSuccess()
            else -> when (it.statusCode) {
                Gloading.STATUS_EMPTY_DATA -> baseActivity.showEmpty()
                else -> baseActivity.showLoadFailed(it.statusCode)
            }
        }
    })
}

/**
 * 绑定 [LoadMoreStatus]
 */
inline fun LiveData<LoadMoreStatus>.bindLoadMoreStatus(baseActivity: LibraryBaseListActivity) {
    observe(baseActivity, Observer {
        when (it) {
            LoadMoreStatus.LOAD_MORE_LOADING -> baseActivity.loadingMore()
            LoadMoreStatus.LOAD_MORE_DISABLE -> baseActivity.disableLoadMore()
            LoadMoreStatus.LOAD_MORE_NO_MORE -> baseActivity.loadMoreNoMore()
            LoadMoreStatus.LOAD_MORE_READY -> baseActivity.loadMoreReady()
            else -> baseActivity.loadMoreFail(it.failCode)
        }
    })
}

/**
 * 在使用 DiffPagedListAdapter 和 DiffPagedListLoadMoreAdapter 的时候使用此方法绑定 LiveData
 */
inline fun <T> LiveData<PagedList<T>>.bindPagedListAdapter(baseActivity: LibraryBaseListActivity, adapter: SubmitDelegate<PagedList<T>>) {
    observe(baseActivity, Observer {
        adapter.submitList(it)
    })
}

/**
 * 在使用 DiffListAdapter 的时候
 */
inline fun <T> LiveData<List<T>>.bindDiffListAdapter(owner: LifecycleOwner, adapter: SubmitDelegate<List<T>>) {
    observe(owner, Observer {
        adapter.submitList(it)
    })
}

/**
 * 在使用 DiffLoadMoreAdapter 的时候，需要使用此方法来绑定 LiveData
 */
inline fun <T> LiveData<List<T>>.bindDiffListLoadMoreAdapter(baseActivity: LibraryBaseListActivity, adapter: SubmitDelegate<List<T>>) {
    observe(baseActivity, Observer {
        adapter.submitList(it, Runnable {
            baseActivity.loadMoreReady()
        })
    })
}

/**
 * 在使用 LoadMoreAdapter 的时候需要使用此方法来绑定 LiveData
 */
inline fun LiveData<List<Any>>.bindLoadMoreAdapter(baseActivity: LibraryBaseListActivity, adapter: LoadMoreAdapter) {
    observe(baseActivity, Observer {
        adapter.items = it
        adapter.notifyDataSetChanged()
    })
}
