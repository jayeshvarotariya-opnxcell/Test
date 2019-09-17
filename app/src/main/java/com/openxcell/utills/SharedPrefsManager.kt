package com.openxcell.utills

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsManager @Inject constructor(private val appContext: Application ) {


    private val PREF_NAME = SharedPreferences::class.java.`package`?.name

    var prefs: SharedPreferences


    init {
        prefs = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    /**
     * Clears all data in SharedPreferences
     */
    fun clearPrefs() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }



    fun removeKey(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun containsKey(key: String): Boolean {
        return prefs.contains(key)
    }

    fun getString(key: String): String {
        return prefs.getString(key, "") ?: ""
    }

    fun getString(key: Int): String {
        return prefs.getString(appContext.getString(key), "") ?: ""
    }

    fun setString(key: String, value: String?) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setString(key: Int, value: String?) {
        val editor = prefs.edit()
        editor.putString(appContext.getString(key), value)
        editor.apply()
    }

    fun <T> setObject(key: Int, value: T) {
        val editor = prefs.edit()
        editor.putString(appContext.getString(key), Gson().toJson(value))
        editor.apply()
    }

    fun <T> getObject(key: Int) {
        val type = object : TypeToken<T>() {
        }.type
        Gson().toJson(prefs.getString(appContext.getString(key), "") ?: "", type)
    }

}