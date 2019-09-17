package com.openxcell.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    abstract fun getBaseViewModel(): BaseViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}