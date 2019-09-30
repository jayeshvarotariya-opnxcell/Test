package com.openxcell.data.api

import android.text.TextUtils
import com.google.gson.Gson
import com.openxcell.R
import com.openxcell.data.pojo.ResponseData
import com.openxcell.utills.SharedPrefsManager
import okhttp3.Interceptor
import okhttp3.Response


class HeaderAndErrorInterceptor(private val sharedPrefsManager: SharedPrefsManager) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val requestBuilder = original.newBuilder()
        // Request customization: add request headers
        if (!TextUtils.isEmpty(sharedPrefsManager.getString(R.string.pref_auth_token)))
            requestBuilder.header(
                URLFactory.KEY_ACCESS_TOKEN,
                sharedPrefsManager.getString(R.string.pref_auth_token)
            ) // <-- this is the important line

        val request = requestBuilder.build()

        val response = chain.proceed(request)

        if (response.code >= 500) {
            throw ServerException(
                Gson().fromJson(
                    response.body!!.string(),
                    ResponseData::class.java
                ).message
            )
        }

        if (response.code >= 401) {
            throw ServerException(
                Gson().fromJson(
                    response.body!!.string(),
                    ResponseData::class.java
                ).message
            )
                .setType(ServerException.Companion.Type.AUTH)
        }

        return response
    }

}