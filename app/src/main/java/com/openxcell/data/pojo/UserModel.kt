package com.openxcell.data.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserModel(
    @PrimaryKey
    var userId: Int,
    var firstName: String? = null,
    var lastName: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneCountryCode: String? = null,
    var referralCode: String? = null,
    var phoneNumber: String,
    var facebookId: String? = null,
    var accessToken: String? = null,
    var profileImage: String = "",
    var registered: Boolean,
    var verified: Boolean,
    var pUserId: Int=0,
    var sUserId: Int=0,
    var profileCreated: Boolean,
    var notificationStatus: Boolean,
    @SerializedName("userProfile")
    var userProfile: UserProfile? = null

)

data class UserProfile(
    var id: String? = null,
    var birthDate: String? = null,
    var gender: String? = null,
    var address: String? = null,
    var town: String? = null,
    var zipcode: String? = null,
    var neighbourhood: String? = null,
    var longitude: String? = null,
    var latitude: String? = null,
    var work: String? = null,
    var grader: String? = null,
    var children: String? = null,
    var catchBuzz: String? = null,
    var smoker: String? = null,
    var smokerPot: String? = null,
    var feed: String? = null,
    var sign: String? = null,
    var religion: String? = null,
    var political: String? = null,
    var pet: String? = null,
    var height: String? = null
)