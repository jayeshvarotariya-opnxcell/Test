package com.openxcell.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.openxcell.databinding.ListFragmentBinding
import com.openxcell.di.Injectable
import com.openxcell.ui.base.BaseFragment
import com.openxcell.ui.base.BaseViewModel

class ListFragment : BaseFragment(), Injectable {
    override fun getBaseViewModel(): BaseViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var mBinding: ListFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= ListFragmentBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}