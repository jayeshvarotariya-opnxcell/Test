package com.openxcell.rx

import io.reactivex.disposables.Disposable

interface RxHandler {

    fun onSubscribe(d: Disposable)

    fun onError(e: Throwable)

}