package com.openxcell.rx.observer

import com.openxcell.rx.RxHandler
import com.openxcell.utills.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class ObserverWithHandler<T>  constructor(val handler : RxHandler) : Observer<T> {

    abstract override fun onNext(t: T)

    override fun onComplete() {
        Logger.log("On Complete")
    }

    override fun onSubscribe(d: Disposable) {
        handler.onSubscribe(d)
    }

    override fun onError(e: Throwable) {
        handler.onError(e)
    }

}