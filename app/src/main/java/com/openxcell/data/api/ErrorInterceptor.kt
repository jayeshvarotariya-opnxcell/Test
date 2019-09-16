package com.openxcell.data.api

import com.google.gson.Gson
import com.openxcell.data.pojo.ResponseData
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Exception

class ErrorInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code >= 500) {
            throw ServerException( Gson().fromJson(response.body!!.string(),ResponseData::class.java).message)
        }

        return response
    }

}