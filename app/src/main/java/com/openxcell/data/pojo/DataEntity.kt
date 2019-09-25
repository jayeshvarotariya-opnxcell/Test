package com.openxcell.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataEntity {

    @Expose
    @SerializedName("avatar")
    var avatar: String? = null
    @Expose
    @SerializedName("last_name")
    var last_name: String? = null
    @Expose
    @SerializedName("first_name")
    var first_name: String? = null
    @Expose
    @SerializedName("email")
    var email: String? = null
    @Expose
    @SerializedName("id")
    var id: Int = 0
    var postImage : String = ""


    fun getName(): String {
        return first_name + " " + last_name
    }

}
