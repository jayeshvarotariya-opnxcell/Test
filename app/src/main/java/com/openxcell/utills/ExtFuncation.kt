package com.openxcell.utills

import com.openxcell.data.api.ServerException
import com.openxcell.data.pojo.ResponseData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.makeThreadSafe() = this.subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .doOnSuccess {
        if (it is ResponseData<*>&&it.status==0)
            throw ServerException(it.message)
    }