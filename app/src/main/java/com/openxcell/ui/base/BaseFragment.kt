package com.openxcell.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.openxcell.ui.activity.MainActivity
import com.openxcell.utills.NavigationCommand


abstract class BaseFragment : Fragment() {

    abstract fun getBaseViewModel(): BaseViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBaseViewModel().navigation.observe(this, Observer {
            when (it) {
                is NavigationCommand.To ->
                    findNavController().navigate(it.directions)
                is NavigationCommand.ToActivity -> {
                    startActivity(Intent(activity, it.type))
                    activity?.finishAffinity()
                }
                is NavigationCommand.Back ->
                    activity?.onBackPressed()

            }
        })

        Intent(activity,MainActivity::class.java)

    }


    val navigator by lazy { findNavController() }

}