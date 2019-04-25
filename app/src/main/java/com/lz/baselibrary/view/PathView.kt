package com.lz.baselibrary.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.lz.baselibrary.LibraryApplication
import com.lz.baselibrary.dp2px

/**
 * @author linzheng
 */
class PathView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val mPath = Path()
    var mWidth: Int = 0
    var mHeight: Int = 0
    val mHalf = 100.dp2px(LibraryApplication.app()).toFloat()
    var mProgress = 0f
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    override fun onDraw(canvas: Canvas) {
        mPath.reset()
        val y = Math.max(mDownY, mHalf)


        mPath.moveTo(0f, y - mHalf)
        mPath.quadTo(y, y + mHalf, 0f, mHalf * mProgress)
        canvas.drawPoint(mHalf * mProgress, y, mPaint)
        canvas.drawPath(mPath, mPaint)
    }


    var mDownX: Float = 0f
    var mDownY: Float = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x
                mDownY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = Math.abs(mDownX - event.x)
                mProgress = Math.min(deltaX / 300, 1f)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                mProgress = 0f
                invalidate()
            }
        }
        return true
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

}