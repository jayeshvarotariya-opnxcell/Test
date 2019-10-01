package com.openxcell.ui.base

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.openxcell.R
import com.openxcell.data.api.ServerException
import com.openxcell.data.db.AppDataBase
import com.openxcell.utills.NavigationCommand
import com.openxcell.rx.RxBus
import com.openxcell.rx.RxHandler
import com.openxcell.utills.SharedPrefsManager
import com.openxcell.utills.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.io.IOException
import javax.inject.Inject

open class BaseViewModel : ViewModel(), RxHandler {



    @Inject
    lateinit var application: Application

    @Inject
    lateinit var appDataBase: AppDataBase

    @Inject
    lateinit var prefsManager: SharedPrefsManager

    @Inject
    lateinit var rxBus: RxBus

    val compositeDisposable = CompositeDisposable()

    val errorLiveData = SingleLiveEvent<String>()

    val progressObservable = ObservableField<Boolean>(false)

    val hideKeyBordObservable = SingleLiveEvent<Boolean>()

    val navigation = SingleLiveEvent<NavigationCommand>()


    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }


    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }


    override fun onError(e: Throwable) {
        when (e) {
            is IOException -> errorLiveData.postValue(application.getString(R.string.no_internet))
            is ServerException ->{
               if (e.type == ServerException.Companion.Type.AUTH)
               {
                   appDataBase.clearAllTables()
                   prefsManager.clearPrefs()

               }
                else
                   errorLiveData.postValue(e.message)
            }

            else -> errorLiveData.postValue(application.getString(R.string.error_comman))

        }
    }


}