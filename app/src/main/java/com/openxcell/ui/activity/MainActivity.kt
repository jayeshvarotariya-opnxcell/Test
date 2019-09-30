package com.openxcell.ui.activity

import com.openxcell.R
import com.openxcell.ui.base.ToolBarActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : ToolBarActivity(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun getMainLayout(): Int = R.layout.activity_main


}