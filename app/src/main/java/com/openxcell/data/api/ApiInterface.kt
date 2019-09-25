package com.openxcell.data.api

import com.openxcell.data.pojo.ResponseData
import com.openxcell.data.pojo.UserModel
import com.openxcell.data.pojo.UserRepo
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST(URLFactory.SIGN_IN_URL)
    fun userLogin(@FieldMap deviceToken: Map<String,String>
    ): Single<ResponseData<UserModel>>

    @GET(URLFactory.USER_PAGE)
    fun getUserListByPage(@Query("page")page: String): Single<UserRepo>

}