package com.openxcell.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.openxcell.BuildConfig
import com.openxcell.rx.MY_DATA
import com.openxcell.rx.RxBus
import com.openxcell.rx.observer.ObserverWithHandler
import com.openxcell.ui.base.ToolBarActivity
import com.openxcell.utills.AESUtills
import com.openxcell.utills.Logger
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : ToolBarActivity(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var rxBus: RxBus

    @Inject
    lateinit var aesUtills: AESUtills


    override fun androidInjector() = dispatchingAndroidInjector

    override fun getMainLayout(): Int = com.openxcell.R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rxBus.scribeWithBusTag<String>(MY_DATA)
            .subscribe(object : ObserverWithHandler<String>(this) {
                override fun onNext(t: String) {
                    Toast.makeText(this@MainActivity, "My String $t", Toast.LENGTH_LONG).show()
                    Logger.log(">>>>>> My String $t")
                }
            })
        rxBus.scribeWithBus<String>()
            .subscribe(object : ObserverWithHandler<String>(this) {
                override fun onNext(t: String) {
                    Toast.makeText(this@MainActivity, "String $t", Toast.LENGTH_LONG).show()
                    Logger.log(">>>>>  String $t")
                }
            })
        rxBus.scribeWithBus<Int>()
            .subscribe(object : ObserverWithHandler<Int>(this) {
                override fun onNext(t: Int) {
                    Toast.makeText(this@MainActivity, "Int $t", Toast.LENGTH_LONG).show()
                    Logger.log(">>>>  Int $t")
                }
            })
        rxBus.scribeWithBus<Any>()
            .subscribe(object : ObserverWithHandler<Any>(this) {
                override fun onNext(t: Any) {
                    Toast.makeText(this@MainActivity, "Any $t", Toast.LENGTH_LONG).show()
                    Logger.log(">>>>>>>  Any $t")
                }
            })


    }





}