package com.lz.baselibrary.view.wrapper.animation

/**
 * WrapperView 的动画生命周期
 * @author linzheng
 */
interface AnimationLifecycle {

    /**
     * 初始化需要执行动画的值
     */
    fun initAnimtaionValue()

    /**
     * WrapperView 显示的时候需要执行的动画
     */
    fun showAnimation()

    /**
     * WrapperView 隐藏的时候需要执行的动画
     */
    fun dismissAnimation()

}