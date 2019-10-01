package com.openxcell.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.openxcell.databinding.LayoutFragmentBinding
import com.openxcell.di.Injectable
import com.openxcell.ui.base.BaseFragment
import com.openxcell.ui.base.BaseViewModel
import com.openxcell.utills.Logger
import com.openxcell.utills.SharedPrefsManager
import javax.inject.Inject

class UserFragment : BaseFragment(), Injectable {



    @Inject lateinit var  viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var sharedPrefsManager: SharedPrefsManager

    lateinit var mBinding: LayoutFragmentBinding


    private val userViewModel: UserViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= LayoutFragmentBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addLiveDataObserver()
        sharedPrefsManager.setString("Hello","hello")
    }


    private fun addLiveDataObserver() {
        mBinding.viewModel=userViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
        userViewModel.getAllList()
            .observe(this, Observer {
                println("This is list count $it" )
                Logger.log("This is list count $it" )
            })

    }



    override fun getBaseViewModel(): BaseViewModel= userViewModel

}