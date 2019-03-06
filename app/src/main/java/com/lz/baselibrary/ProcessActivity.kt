package com.lz.baselibrary

import android.os.Bundle
import com.lz.baselibrary.base.LibraryBaseActivity

/**
 * @author linzheng
 */
class ProcessActivity : LibraryBaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)
    }

}