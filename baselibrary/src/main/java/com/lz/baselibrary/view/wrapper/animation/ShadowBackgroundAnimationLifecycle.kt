package com.lz.baselibrary.view.wrapper.animation

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import com.lz.baselibrary.view.wrapper.BaseWrapperView

/**
 * WrapperView 的阴影背景动画
 * @author linzheng
 */
class ShadowBackgroundAnimationLifecycle(
        wrapperView: BaseWrapperView
) : BaseAnimationLifecycle(wrapperView) {

    private val startColor = Color.TRANSPARENT
    private val endColor = Color.parseColor("#77000000")
    private val argbEvaluator = ArgbEvaluator()

    override fun initAnimtaionValue() {
        wrapperView.setBackgroundColor(startColor)
    }

    override fun showAnimation() {
        createAnimator(startColor, endColor).start()
    }

    override fun dismissAnimation() {
        createAnimator(endColor, startColor).start()
    }

    private fun createAnimator(startValue: Int, endValue: Int): ValueAnimator = ValueAnimator.ofObject(argbEvaluator, startValue, endValue).apply {
        addUpdateListener {
            wrapperView.setBackgroundColor(it.animatedValue as Int)
        }
        duration = BaseWrapperView.ANIMATION_DURATION
    }

}