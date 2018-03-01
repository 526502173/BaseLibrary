package com.lz.baselibrary.view

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * <pre>
 * author : Think
 * e-mail : 1007687534@qq.com
 * time   : 2018/02/09
 * desc   : 水平分割线
 * version: 1.0
</pre> *
 */
class VerticalItemDecoration : BaseItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.set(0, 0, 0, 10)
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        drawVertical(c!!, parent!!)
    }

}
