package com.lz.baselibrary.base

import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * @author linzheng
 */
open abstract class LibraryLazyLoadFragment : LibraryBaseFragment() {

    /**
     * 是否初始化
     */
    private var mIsInitial = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint && !mIsInitial) {
            mIsInitial = !mIsInitial
            //因为在此方法中，Fragment 的 View 没有创建成果
            //所以这里需要延时一段时间执行
            Observable.timer(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoDisposable(mScopeProvider)
                    .subscribe { lazyLoad() }
        }
    }

    open abstract fun lazyLoad()

}