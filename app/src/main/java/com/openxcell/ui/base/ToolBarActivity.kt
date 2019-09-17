package com.openxcell.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_container.*


abstract class ToolBarActivity :BaseActivity() {

    private lateinit var  drawerLayout:DrawerLayout
    private lateinit var  appBar:AppBarLayout
    private lateinit var toolBar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intToolBarDrawerLayout()
    }

    private fun intToolBarDrawerLayout() {

        //init variable so we can access from here in to child class
        drawerLayout=drawerLayoutMain
        appBar=appBarMain
        toolBar=toolbarMain

        // configure for 1st time use
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED)
        setSupportActionBar(toolBar)
        appBar.visibility= View.GONE

    }




}