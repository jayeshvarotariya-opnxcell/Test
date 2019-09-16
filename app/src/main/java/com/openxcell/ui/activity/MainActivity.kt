package com.openxcell.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.openxcell.R
import com.openxcell.di.builder.ViewModelKey
import com.openxcell.ui.user.UserFragment
import com.openxcell.ui.base.ToolBarActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector

import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : ToolBarActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun getMainLayout(): Int = R.layout.activity_main






}