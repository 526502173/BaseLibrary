package com.lz.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.*
import androidx.fragment.app.DialogFragment
import com.lz.baselibrary.R


/**
 * BaseDialogFragment
 * @author linzheng
 */
open abstract class BaseDialogFragment : DialogFragment() {

    private var mWidth: Int = 0

    private var mHeight: Int = 0

    private var mHorizontalMargin: Int = 0

    private var mDimAmount: Int = 0

    private var mIsShowBottom = false

    private var mIsCancelable = true

    protected var mView: View? = null

    protected abstract val layoutResID: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(androidx.fragment.app.DialogFragment.STYLE_NO_TITLE, R.style.BaseDialogFramgent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater!!.inflate(layoutResID, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsAndEvents()
    }

    override fun onStart() {
        super.onStart()
        dialog.setCanceledOnTouchOutside(true)
        if (dialog.window != null) {
            setParams()
            val window = dialog.window
            val lp = window!!.attributes
            lp.dimAmount = if (mDimAmount == 0) 0.5f else mDimAmount.toFloat()
            lp.gravity = if (mIsShowBottom) Gravity.BOTTOM else Gravity.CENTER
            lp.width = if (mWidth == 0) getScreenWidth(context!!) - 2 * mHorizontalMargin else mWidth
            lp.height = if (mHeight == 0) WindowManager.LayoutParams.WRAP_CONTENT else mHeight
            window.setWindowAnimations(R.style.DefaultAnimation)
            window.attributes = lp
            isCancelable = mIsCancelable
        }
    }

    protected abstract fun setParams()

    protected abstract fun initViewsAndEvents()

    fun show(fragmentTransaction: androidx.fragment.app.FragmentManager) {
        show(fragmentTransaction, SystemClock.currentThreadTimeMillis().toString())
    }

    // ---------------- getter/setter --------------------
    fun getWidth(): Int {
        return mWidth
    }

    fun setWidth(width: Int): BaseDialogFragment {
        mWidth = width
        return this
    }

    fun getHeight(): Int {
        return mHeight
    }

    fun setHeight(height: Int): BaseDialogFragment {
        mHeight = height
        return this
    }

    fun getHorizontalMargin(): Int {
        return mHorizontalMargin
    }

    fun setHorizontalMargin(horizontalMargin: Int): BaseDialogFragment {
        mHorizontalMargin = horizontalMargin
        return this
    }

    fun getDimAmount(): Int {
        return mDimAmount
    }

    fun setDimAmount(dimAmount: Int): BaseDialogFragment {
        mDimAmount = dimAmount
        return this
    }

    fun isShowBottom(): Boolean {
        return mIsShowBottom
    }

    fun setShowBottom(showBottom: Boolean): BaseDialogFragment {
        mIsShowBottom = showBottom
        return this
    }

    override fun isCancelable(): Boolean {
        return mIsCancelable
    }

    fun setIsCancelable(cancelable: Boolean): BaseDialogFragment {
        mIsCancelable = cancelable
        return this
    }

    private fun getScreenWidth(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }

}
