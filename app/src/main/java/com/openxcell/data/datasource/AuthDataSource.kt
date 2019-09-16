package com.openxcell.data.datasource


import androidx.lifecycle.LiveData
import com.openxcell.data.api.ApiInterface
import com.openxcell.data.db.UserDao
import com.openxcell.data.pojo.ResponseData
import com.openxcell.data.pojo.UserModel
import com.openxcell.data.repository.AuthRepository
import com.openxcell.utills.SubscribeWithOutType
import com.openxcell.utills.makeThreadSafe
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import java.util.*
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val apiInterface: ApiInterface,
    private val userDao: UserDao
) : AuthRepository {


    override fun getAll(): LiveData<List<UserModel>> = userDao.getAllList()

    override fun userLogin(email:String, password:String): Single<ResponseData<UserModel>> {
        val valuesMap = HashMap<String, String>()
        valuesMap["deviceToken"] = "eUHuPNmygJ8:APA91bGmFECbbWx62-xCAllYI2fBi-AafindpATHAoQhpITMMSznyauwCpYqjuXBy0yRCqdj5N34pnTGeGv1C8tzTvq7sTU_4AxqPSdhcXtmKO-D6vwWLndV_7VRCL0ZxJ0qsN-EECX7"
        valuesMap["deviceType"] = "android"
        valuesMap["userName"] = email
        valuesMap["socialId"] = ""
        valuesMap["password"] = password
        valuesMap["loginType"] = "normal"
        return apiInterface.userLogin(valuesMap).makeThreadSafe()
            .doOnSuccess {
                insertCryptocurrency(it.data)
            }
    }

    private fun insertCryptocurrency(user: UserModel?) {
        Single.create(object : SingleOnSubscribe<Boolean> {
            override fun subscribe(e: SingleEmitter<Boolean>) {
                user?.let {
                    userDao.insertUser(user)
                }

            }
        }).makeThreadSafe()
            .subscribe(SubscribeWithOutType())
    }


}