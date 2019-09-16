package com.openxcell.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // DaggerActivityComponent
        setContentView(getMainLayout())
    }

    abstract fun getMainLayout(): Int

}