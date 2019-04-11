package com.lz.baselibrary.utils.rxjava

import com.lz.baselibrary.api.RespWrapper
import com.lz.baselibrary.base.ListView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * 有下拉刷新但是不需要分页的 Transformer
 * @author linzheng
 */
class RefreshResponseTransformer<T>(
        private val mListView: ListView
) : ObservableTransformer<RespWrapper<T>, T> {
    override fun apply(upstream: Observable<RespWrapper<T>>): Observable<T> = upstream.doOnComplete {
        mListView.showSuccess()
    }.doFinally {
        mListView.refreshComplete()
    }.map {
        it.data
    }
}