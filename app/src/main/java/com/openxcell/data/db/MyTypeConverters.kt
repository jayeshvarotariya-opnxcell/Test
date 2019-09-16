package com.openxcell.data.db

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.openxcell.data.pojo.UserProfile

object MyTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringTOUserProfile(userProfileString: String): UserProfile {
        if (TextUtils.isEmpty(userProfileString))
            return UserProfile()
        else
            return Gson().fromJson(userProfileString, UserProfile::class.java)
    }


    @TypeConverter
    @JvmStatic
    fun userProfileToString(userProfile: UserProfile?): String {
        return if (userProfile == null)
            ""
        else
            Gson().toJson(userProfile)
    }

}