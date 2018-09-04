package com.lz.baselibrary.utils.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

/**
 * RecyclerView Item 的点击事件
 * @author linzheng
 */
class RecyclerViewItemClickListener(
        private val recyclerView: RecyclerView,
        private val listener: OnItemClickListener
) : RecyclerView.OnItemTouchListener {

    private val mGestureDetector by lazy {
        GestureDetector(recyclerView.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                val child = recyclerView.findChildViewUnder(e?.x!!, e.y!!)
                //判断 Item 是否需要分发事件
                if (child != null && !child.dispatchTouchEvent(e)) {
                    val position = recyclerView.getChildLayoutPosition(child)
                    listener.onItemClick(child, position)
                }
                return super.onSingleTapUp(e)
            }

            override fun onLongPress(e: MotionEvent?) {
                val child = recyclerView.findChildViewUnder(e?.x!!, e?.y!!)
                if (child != null) {
                    listener.onItemLongClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }

    init {
        //非常扯犊子的一个问题
        val ts = ViewConfiguration.get(recyclerView.context).scaledTouchSlop
        val touchSlop = mGestureDetector::class.java.getDeclaredField("mTouchSlopSquare").apply { isAccessible = true }
        touchSlop.set(mGestureDetector, ts)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }


    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
    }

}

interface OnItemClickListener {

    fun onItemClick(view: View, position: Int)

    fun onItemLongClick(view: View, position: Int)

}