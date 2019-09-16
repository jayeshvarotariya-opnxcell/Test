package com.openxcell.ui.base

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.openxcell.R
import com.openxcell.data.api.ServerException
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var application: Application

    val compositeDisposable = CompositeDisposable()

    val errorLiveData = ObservableField<String>()


    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }


    fun onError(e: Throwable) {
        when (e) {
            is IOException -> errorLiveData.set(application.getString(R.string.no_internet))
            is ServerException -> errorLiveData.set(e.message)
            else -> errorLiveData.set(application.getString(R.string.error_comman))

        }
        errorLiveData.notifyChange()
    }


}