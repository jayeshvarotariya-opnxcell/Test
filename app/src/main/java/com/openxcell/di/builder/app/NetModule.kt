package com.openxcell.di.builder.app

import com.openxcell.data.api.URLFactory
import com.openxcell.data.api.ApiInterface
import com.openxcell.data.api.HeaderAndErrorInterceptor
import com.openxcell.utills.SharedPrefsManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(sharedPrefsManager: SharedPrefsManager): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HeaderAndErrorInterceptor(sharedPrefsManager))
        .addInterceptor(provideHttpLoggingInterceptor())
        .build()

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val ceptor = HttpLoggingInterceptor()
        ceptor.level = HttpLoggingInterceptor.Level.BODY
        return ceptor
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(URLFactory.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    @Provides
    @Singleton
    fun provideApiInterface (retrofitClient: Retrofit) : ApiInterface = retrofitClient.create(
        ApiInterface::class.java)


}
