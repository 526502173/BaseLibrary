package com.lz.baselibrary.view.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Scroller
import androidx.core.view.children
import timber.log.Timber

/**
 * 自定义 ViewGroup 实现类似 ViewPager 的功能
 * @author linzheng
 */
class CustomViewPager : ViewGroup {

    var mCurrentIndex = 0

    val mScroller by lazy {
        Scroller(context)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        children.forEachIndexed { index, view ->
            if (index == getLeftIndex()) {
                view.layout(-view.measuredWidth, 0, 0, view.measuredHeight)
            } else {
                if (index == mCurrentIndex) {
                    view.layout(0, 0, view.measuredWidth, view.measuredHeight)
                } else {
                    val left = if (index > mCurrentIndex) {
                        (index - mCurrentIndex) * view.measuredWidth
                    } else {
                        val lastChildIndex = childCount - 1
                        (lastChildIndex - mCurrentIndex + index) * view.measuredWidth
                    }
                    val right = left + view.measuredWidth
                    view.layout(left, 0, right, view.measuredHeight)
                }
            }
        }
    }

    fun getLeftIndex() = if (mCurrentIndex == 0) childCount - 1 else mCurrentIndex - 1


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    var mDownX = 0

    var mLastScrollX = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = -(event.x - mDownX).toInt()
                Timber.d("CustomViewPager scrollTo x = ${moveX * -1 + scrollX}")
                scrollTo(mLastScrollX + moveX, 0)
            }
            MotionEvent.ACTION_UP -> {

                mLastScrollX = scrollX
            }
        }
        return true
    }


//    override fun computeScroll() {
//        if (mScroller.computeScrollOffset()) {
//            scrollTo(mScroller.currX, mScroller.currY)
//            invalidate()
//        }
//    }


}