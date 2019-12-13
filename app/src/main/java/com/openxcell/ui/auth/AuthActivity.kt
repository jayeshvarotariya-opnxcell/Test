package com.openxcell.ui.auth

import android.os.Bundle
import com.openxcell.R
import com.openxcell.ui.base.ToolBarActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class AuthActivity : ToolBarActivity(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector() = dispatchingAndroidInjector

    override fun getMainLayout(): Int = R.layout.activity_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolBarModeFullScreen(true)

    }


}