package com.openxcell.ui.home

import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.openxcell.rx.MY_DATA
import com.openxcell.rx.RxBus
import com.openxcell.rx.observer.ObserverWithHandler
import com.openxcell.ui.base.ToolBarActivity
import com.openxcell.utills.AESUtills
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
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


        initNavigationSlider()

        rxBus.scribeWithBusTag<String>(MY_DATA)
            .subscribe(object : ObserverWithHandler<String>(this) {
                override fun onNext(t: String) {

                }
            })
        rxBus.scribeWithBus<String>()
            .subscribe(object : ObserverWithHandler<String>(this) {
                override fun onNext(t: String) {

                }
            })
        rxBus.scribeWithBus<Int>()
            .subscribe(object : ObserverWithHandler<Int>(this) {
                override fun onNext(t: Int) {

                }
            })
        rxBus.scribeWithBus<Any>()
            .subscribe(object : ObserverWithHandler<Any>(this) {
                override fun onNext(t: Any) {

                }
            })


    }

    private fun initNavigationSlider() {
        //init variable so we can access from here in to child class
        drawerLayout = drawerLayoutMain
    }


}