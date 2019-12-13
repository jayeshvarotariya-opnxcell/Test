package com.openxcell.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.maan.mandir.utills.MessageCommand
import com.openxcell.R
import com.openxcell.databinding.TostLayoutBinding
import com.openxcell.ui.home.MainActivity
import com.openxcell.utills.NavigationCommand


abstract class BaseFragment : Fragment() {

    abstract fun getBaseViewModel(): BaseViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBaseViewModel().navigation.observe(this, Observer {
            when (it) {
                is NavigationCommand.To ->
                    navigator.navigate(it.directions)
                is NavigationCommand.ToActivity -> {
                    startActivity(Intent(activity, it.type))
                    activity?.finishAffinity()
                }
                is NavigationCommand.Back ->
                    activity?.onBackPressed()
            }
        })

        Intent(activity, MainActivity::class.java)

    }


    private fun errorMessageLiveEvent() {
        getBaseViewModel().messageLiveData.observe(this, Observer { message ->



            var messageStr = ""
            var messageColor = 0

            when (message) {

                is MessageCommand.Info -> {
                    messageStr = message.message
                }

                is MessageCommand.Error -> {
                    messageStr = message.message
                    messageColor = R.color.red
                }

                is MessageCommand.Warn -> {
                    messageStr = message.message
                    messageColor = R.color.yallow
                }

                is MessageCommand.Success -> {
                    messageStr = message.message
                    messageColor = R.color.green
                }

                is MessageCommand.NoInternet -> {
                    messageStr = getString(R.string.no_internet)
                    messageColor = R.color.red
                }

                is MessageCommand.SomethingWantWrong -> {
                    messageStr = getString(R.string.error_comman)
                    messageColor = R.color.yallow
                }

                is MessageCommand.Non -> {
                    //Do nothing
                }
            }


            val viewBinding: TostLayoutBinding =
                TostLayoutBinding.inflate(layoutInflater)
            viewBinding.textViewTitle.setText("$messageStr")
            viewBinding.textViewTitle.setBackgroundResource(messageColor)
            val toast = Toast(getActivity()?.applicationContext)
            toast.setGravity(
                Gravity.BOTTOM or Gravity.FILL_HORIZONTAL,
                0,
                0
            )
            toast.duration = Toast.LENGTH_LONG
            toast.view = viewBinding.root
            if (message !is MessageCommand.Non && message is MessageCommand)
                toast.show()

        })
    }



    val navigator by lazy { findNavController() }


    /**
     * manageCut = Do you want to manage cut out status bar size by your fragment layout or use default
     * return size of display status bar with cut out
     */
    fun setToolBarModeFullScreen(manageCut: Boolean = false, bottomMenu: Boolean = true): Int {

        return if (activity is ToolBarActivity)
            (activity as ToolBarActivity).setToolBarModeFullScreen(manageCut)
        else
            0

    }


    fun setToolBarModeBack(
        tital: String,
        icon: Int = R.drawable.ic_back_arrow,
        bottomMenu: Boolean = true
    ) {
        if (activity is ToolBarActivity)
            (activity as ToolBarActivity).setToolBarModeBack(tital, icon)

    }

    fun getNotchSize(): LiveData<Int> {
        if (activity is ToolBarActivity)
            return (activity as ToolBarActivity).getNotchSize()
        else
            return MutableLiveData(0)
    }


}