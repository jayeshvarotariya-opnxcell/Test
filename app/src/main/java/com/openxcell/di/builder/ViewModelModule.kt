package com.openxcell.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.openxcell.ui.list.ListViewModel
import com.openxcell.ui.list.PageViewModel
import com.openxcell.viewmodel.ViewModelFactory
import com.openxcell.ui.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(userViewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PageViewModel::class)
    abstract fun bindPageViewModel(pageViewModel: PageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
