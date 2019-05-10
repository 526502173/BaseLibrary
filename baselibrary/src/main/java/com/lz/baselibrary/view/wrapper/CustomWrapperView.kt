package com.lz.baselibrary.view.wrapper

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.lz.baselibrary.R
import com.lz.baselibrary.view.wrapper.animation.AnimationLifecycle
import com.lz.baselibrary.view.wrapper.animation.ShadowBackgroundAnimationLifecycle

/**
 * @author linzheng
 */

class CustomWrapperView : BaseWrapperView {

    override val animationLifecycleList: List<AnimationLifecycle>
        get() {
            if (_animationLifecycleList == null) {
                _animationLifecycleList = listOf(ShadowBackgroundAnimationLifecycle(this))
            }
            return _animationLifecycleList ?: throw AssertionError("Set to null by another thread")
        }

    override val contentViewList: List<View>
        get() {
            if (_contentViewList == null) {
                _contentViewList = buildContentView(R.layout.wrapper_view_test)
            }
            return _contentViewList ?: throw AssertionError("Set to null by another thread")
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}