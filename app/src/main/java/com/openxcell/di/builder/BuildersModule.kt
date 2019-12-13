package com.openxcell.di.builder

import com.openxcell.ui.SplashActivity
import com.openxcell.ui.auth.AuthActivity
import com.openxcell.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesSplashActivity(): SplashActivity


}