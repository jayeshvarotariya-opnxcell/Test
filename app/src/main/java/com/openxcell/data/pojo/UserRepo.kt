package com.openxcell.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class UserRepo {


    @Expose
    @SerializedName("data")
    var data: List<DataEntity>? = null
    @Expose
    @SerializedName("total_pages")
    var total_pages: Int = 0
    @Expose
    @SerializedName("total")
    var total: Int = 0
    @Expose
    @SerializedName("per_page")
    var per_page: Int = 0
    @Expose
    @SerializedName("page")
    var page: Int = 0
}
