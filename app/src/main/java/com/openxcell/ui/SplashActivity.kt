package com.openxcell.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.openxcell.R
import com.openxcell.ui.auth.AuthActivity
import com.openxcell.ui.home.MainActivity
import com.openxcell.utills.SharedPrefsManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {


    val handler = Handler()

    lateinit var run: Runnable

    @Inject
    lateinit var prefsManager: SharedPrefsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        run = Runnable {
            if (TextUtils.isEmpty(prefsManager.getString(SharedPrefsManager.AUTH_TOKEN)))
                startActivity(Intent(this, AuthActivity::class.java))
            else
                startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
    }


    override fun onResume() {
        super.onResume()
        if (::run.isInitialized)
            handler.postDelayed(run, 3000)
    }


    override fun onPause() {
        handler.removeCallbacks(run)
        super.onPause()
    }


}