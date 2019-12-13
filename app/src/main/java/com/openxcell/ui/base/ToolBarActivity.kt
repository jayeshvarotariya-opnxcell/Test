package com.openxcell.ui.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.appbar.AppBarLayout
import com.openxcell.R
import kotlinx.android.synthetic.main.main_container.*


abstract class ToolBarActivity :BaseActivity() {


    private lateinit var appBar: AppBarLayout
    private lateinit var toolBar: Toolbar
    private var manageCut: Boolean = false
    private var cutSize = 0
    var drawerLayout: DrawerLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        readCutOutSize()
        intToolBarDrawerLayout()

    }

    private fun intToolBarDrawerLayout() {
        appBar = appBarMain
        toolBar = toolbarMain
        setSupportActionBar(toolBar)
        showHideToolBar()
        // configure for 1st time use
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun showHideToolBar(show: Boolean = true, manageCut: Boolean = false) {
        mainContainer.view?.setPadding(0, 0, 0, 0)
        this.manageCut = manageCut;
        if (show) {
            appBar.visibility = View.VISIBLE
            val params = mainContainer.view?.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = AppBarLayout.ScrollingViewBehavior()
            mainContainer.view?.requestLayout()
        } else {
            appBar.visibility = View.GONE
            val params = mainContainer.view?.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = null
            mainContainer.view?.requestLayout()
            if (!manageCut && cutSize != 0)
                mainContainer.view?.setPadding(0, cutSize, 0, 0)
            else if (!manageCut) {

            }
        }
    }

    @SuppressLint("NewApi")
    private fun readCutOutSize() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            /*   val cutout = windowManager.defaultDisplay.cutout
               cutout?.getBoundingRects()?.get(0)?.height()?.let {
                   cutSize = it
               } ?: run {
                   cutSize = resources.getDimensionPixelSize(com.maan.mandir.R.dimen._24sdp)
               }
   */
            appBarMain?.setOnApplyWindowInsetsListener { v: View?, insets: WindowInsets? ->
                val cutout = insets?.displayCutout
                cutout?.getBoundingRects()?.get(0)?.height()?.let {
                    cutSize = it
                    mcutData.postValue(it)
                } ?: run {
                    cutSize = resources.getDimensionPixelSize(R.dimen._24sdp)
                    mcutData.postValue(resources.getDimensionPixelSize(R.dimen._24sdp))
                }
                if (!manageCut)
                    mainContainer.view?.setPadding(0, cutSize, 0, 0)
                return@setOnApplyWindowInsetsListener insets
            }
        } else {
            cutSize = resources.getDimensionPixelSize(R.dimen._24sdp)
        }
    }

    /**
     * manageCut = Do you want to manage cut out status bar size by your fragment layout or use default
     * return size of display status bar with cut out
     */
    fun setToolBarModeFullScreen(manageCut: Boolean): Int {
        showHideToolBar(false, manageCut)
        return cutSize
    }

    val mcutData = MutableLiveData(0)

    fun getNotchSize(): LiveData<Int> {

        mcutData.postValue(cutSize)
        return mcutData
    }

    fun setToolBarModeBack(text: String, icon: Int) {
        showHideToolBar(true)
        toolBar.setNavigationIcon(icon)
        toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        textViewTitle.text = text
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


}