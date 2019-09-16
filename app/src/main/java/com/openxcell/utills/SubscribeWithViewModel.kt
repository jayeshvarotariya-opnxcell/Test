package com.openxcell.utills

import com.openxcell.ui.base.BaseViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

 abstract class SubscribeWithModel  <T,V : BaseViewModel >  constructor(val model: V ) : SingleObserver<T> {


    abstract override fun onSuccess(t: T)

    override fun onSubscribe(d: Disposable) {
        model.compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
      model.onError(e)
    }

}