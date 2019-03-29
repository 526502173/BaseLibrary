package com.lz.baselibrary.view.recyclerview

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView Item 的点击事件
 * 建议使用该类来替换在 RecyclerView 的 onBinderViewHolder() 方法中直接使用 setOnClickListener() 方法
 * @author linzheng
 */
class RecyclerViewItemClickListener(
        private val recyclerView: androidx.recyclerview.widget.RecyclerView,
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

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return mGestureDetector.onTouchEvent(e)
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }


}