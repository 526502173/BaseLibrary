package com.lz.baselibrary.utils.rxjava

import com.lz.baselibrary.base.ListView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * @author linzheng
 */
class ResponseTransformer<T>(private val mListView: ListView) : ObservableTransformer<T,T>{
    override fun apply(upstream: Observable<T>): Observable<T> = upstream.doOnComplete {
        mListView.showSuccess()
    }
}