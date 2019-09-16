package com.openxcell.di.builder.app

import com.openxcell.data.datasource.AuthDataSource
import com.openxcell.data.repository.AuthRepository
import com.openxcell.di.builder.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetModule::class,DataBaseModule::class,ViewModelModule::class])
class AppModule {


    @Provides
    @Named("db_name")
    fun provideDataBaseName() : String ="my_app_db"


    @Provides
    @Singleton
    fun provideAuthRepository (authDataSource: AuthDataSource) :AuthRepository = authDataSource


}