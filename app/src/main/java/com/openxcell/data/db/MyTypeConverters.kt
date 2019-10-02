package com.openxcell.data.db

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.openxcell.data.pojo.PriceEntity

object MyTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringTOUserProfile(userProfileString: String): PriceEntity {
        if (TextUtils.isEmpty(userProfileString))
            return PriceEntity()
        else
            return Gson().fromJson(userProfileString, PriceEntity::class.java)
    }


    @TypeConverter
    @JvmStatic
    fun userProfileToString(userProfile: PriceEntity?): String {
        return if (userProfile == null)
            ""
        else
            Gson().toJson(userProfile)
    }

}