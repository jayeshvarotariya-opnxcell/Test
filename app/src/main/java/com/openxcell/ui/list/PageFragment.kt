package com.openxcell.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.openxcell.databinding.PageFragmentBinding
import com.openxcell.di.Injectable
import com.openxcell.ui.base.BaseFragment
import javax.inject.Inject

class PageFragment : BaseFragment(), Injectable {



    @Inject
    lateinit var  viewModelFactory: ViewModelProvider.Factory


    private val listViewModel: PageViewModel by viewModels {
        viewModelFactory
    }


    override fun getBaseViewModel() = listViewModel


    lateinit var mBinding : PageFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= PageFragmentBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel =listViewModel
        listViewModel.retrieveDataList()
    }


}