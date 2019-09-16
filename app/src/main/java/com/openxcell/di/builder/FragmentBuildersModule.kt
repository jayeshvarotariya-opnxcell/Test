package com.openxcell.di.builder

import com.openxcell.ui.list.ListFragment
import com.openxcell.ui.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

}
