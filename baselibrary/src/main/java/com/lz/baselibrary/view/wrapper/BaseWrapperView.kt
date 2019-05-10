package com.lz.baselibrary.view.wrapper

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.lz.baselibrary.view.wrapper.animation.AnimationLifecycle

/**
 *
 * WrapperView 可以包裹任何 View 并显示在 Activity/Fragment 顶层的 View
 * 继承自 ConstraintLayout
 * 参考 https://github.com/li-xiaojun/XPopup
 * @author linzheng
 */
open abstract class BaseWrapperView : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected var _contentViewList: List<View>? = null

    abstract val contentViewList: List<View>

    protected var _animationLifecycleList: List<AnimationLifecycle>? = null

    abstract val animationLifecycleList: List<AnimationLifecycle>

    init {
        contentViewList.forEach { addView(it) }
        animationLifecycleList.forEach { it.initAnimtaionValue() }
    }

    fun buildContentView(layoutResID: Int) = buildContentViewList(listOf(layoutResID))

    fun buildContentViewList(layoutResIDList: List<Int>) = LayoutInflater.from(context).run {
        val result = mutableListOf<View>()
        layoutResIDList.flatMap {
            val contentView = inflate(it, this@BaseWrapperView, false)
            if (contentView is ConstraintLayout) contentView.children.toList().apply { contentView.removeAllViews() }
            else listOf(contentView)
        }.forEach { result.add(it) }
        result
    }


    fun show() {
        val activity = context as Activity
        val decorView = activity.window.decorView as ViewGroup

        decorView.post {
            //必须设置，不然无法捕捉到键盘事件
            isFocusableInTouchMode = true
            requestFocus()
            setOnKeyListener { view, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                    dismiss()
                    return@setOnKeyListener true
                }
                false
            }
            decorView.addView(this, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))

            animationLifecycleList.forEach {
                it.showAnimation()
            }
        }
    }

    fun dismiss() {
        animationLifecycleList.forEach { it.dismissAnimation() }
        postDelayed(Runnable { removeWrapperView() }, ANIMATION_DURATION)
    }

    private fun removeWrapperView() {
        val activity = context as Activity
        val decorView = activity.window.decorView as ViewGroup
        decorView.removeView(this)
    }

    companion object {

        val ANIMATION_DURATION = 500L

    }

}