package com.openxcell.di.builder

import com.openxcell.ui.list.ListFragment
import com.openxcell.ui.list.PageFragment
import com.openxcell.ui.list.SwipeFragment
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

    @ContributesAndroidInjector
    abstract fun contributeSwipeFragment(): SwipeFragment

    @ContributesAndroidInjector
    abstract fun contributePageFragment(): PageFragment

}
