package com.lz.baselibrary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lz.baselibrary.R
import com.lz.baselibrary.base.LibraryBaseFragment
import kotlinx.android.synthetic.main.fragment_lazy_load.*

/**
 * @author linzheng
 */
class LazyLoadFragment : LibraryBaseFragment() {

    lateinit var mTitle: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = mTitle
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        mTitle = arguments?.getString("title")!!
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lazy_load,container,false)
    }

    override fun loadData() {
        // load Data
    }

    companion object {
        fun newInstance(title: String): LazyLoadFragment {
            val args = Bundle()
            args.putString("title", title)
            val fragment = LazyLoadFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
