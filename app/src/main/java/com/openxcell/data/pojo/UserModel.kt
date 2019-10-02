package com.openxcell.data.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class UserModel(

    @Expose
    @SerializedName("subscribed")
    var subscribed: Boolean = false,
    @Expose
    @SerializedName("remainingCount")
    var remainingCount: Int = 0,
    @Expose
    @SerializedName("viewCount")
    var viewCount: Int = 0,
    @Expose
    @SerializedName("adminVerification")
    var adminVerification: String? = null,
    @Expose
    @SerializedName("billing")
    var billing: String? = null,
    @Expose
    @SerializedName("subscriptionExpireDate")
    var subscriptionExpireDate: String? = null,
    @Expose
    @SerializedName("amount")
    var amount: Double = 0.toDouble(),
    @Expose
    @SerializedName("subscriptionActivationDate")
    var subscriptionActivationDate: String? = null,
    @Expose
    @SerializedName("notificationStatus")
    var notificationStatus: Boolean = false,
    @Expose
    @SerializedName("countryName")
    var countryName: String? = null,
    @Expose
    @SerializedName("registered")
    var registered: Boolean = false,
    @Expose
    @SerializedName("profileCompleted")
    var profileCompleted: Boolean = false,
    @Expose
    @SerializedName("price")
    var price: PriceEntity? = null,
    @Expose
    @SerializedName("emailVerified")
    var emailVerified: String? = null,
    @Expose
    @SerializedName("mobileVerified")
    var mobileVerified: String? = null,
    @Expose
    @SerializedName("mobile")
    var mobile: String? = null,
    @Expose
    @SerializedName("mobileCode")
    var mobileCode: String? = null,
    @Expose
    @SerializedName("profileImage")
    var profileImage: String? = null,
    @Expose
    @SerializedName("accessToken")
    var accessToken: String? = null,
    @Expose
    @SerializedName("email")
    var email: String? = null,
    @Expose
    @SerializedName("lastName")
    var lastName: String? = null,
    @Expose
    @SerializedName("firstName")
    var firstName: String? = null,
    @PrimaryKey
    @Expose
    @SerializedName("id")
    var id: Int = 0

)
