package com.openxcell.data.repository

import androidx.lifecycle.LiveData
import com.openxcell.data.pojo.CryptocurrencyEntity
import com.openxcell.data.pojo.ResponseData
import com.openxcell.data.pojo.UserModel
import com.openxcell.data.pojo.UserRepo
import io.reactivex.Observable
import io.reactivex.Single

interface AuthRepository {

    fun userLogin(email:String,password:String): Single<ResponseData<UserModel>>

    fun getAll(): LiveData<List<UserModel>>

    fun getUserListByPage(s: String) : Single<UserRepo>

}