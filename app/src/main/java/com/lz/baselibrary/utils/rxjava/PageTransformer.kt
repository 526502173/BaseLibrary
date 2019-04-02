package com.lz.baselibrary.utils.rxjava

import com.lz.baselibrary.base.ListView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

/**
 * @author linzheng
 */
class PageTransformer<T>(
        private val mListView: ListView,
        private val mResponseTransformer: ResponseTransformer<T> = ResponseTransformer(mListView)
) : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): Observable<T> = mResponseTransformer.apply(upstream).doOnNext {
        mListView.loadMoreNormal()
    }.doFinally {
        mListView.refreshComplete()
    }

}