package com.openxcell.data.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity
data class CryptocurrencyEntity  (

    @Expose
    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String?

    // ... other attributes

)