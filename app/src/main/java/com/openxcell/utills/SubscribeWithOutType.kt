package com.openxcell.utills

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SubscribeWithOutType<T> : SingleObserver<T> {


    override fun onSuccess(t: T) {
        Logger.log(">>>>>>>>>>>>>>>>>> onSuccess")
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onError(e: Throwable) {
        Logger.log(">>>>>>>>>>>>>>>>>> onError"+e.message)
    }

}