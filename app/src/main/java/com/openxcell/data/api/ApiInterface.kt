package com.openxcell.data.api

import com.openxcell.data.pojo.ResponseData
import com.openxcell.data.pojo.UserModel
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST(URLFactory.SIGN_IN_URL)
    fun userLogin(@FieldMap deviceToken: Map<String,String>
    ): Single<ResponseData<UserModel>>

}