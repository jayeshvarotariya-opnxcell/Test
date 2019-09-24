package com.openxcell.ui.user

import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.openxcell.R
import com.openxcell.data.pojo.ResponseData
import com.openxcell.data.pojo.UserModel
import com.openxcell.data.repository.AuthRepository
import com.openxcell.ui.base.BaseViewModel
import com.openxcell.utills.Logger
import com.openxcell.utills.NavigationCommand
import com.openxcell.utills.SubscribeWithModel
import java.util.regex.Pattern
import javax.inject.Inject

class UserViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {


    val email = ObservableField<String>("rahul.sadhu@openxcelltechnolabs.com")
    val password = ObservableField<String>("123456")
    val emailError = ObservableField<String>("")
    val passwordError = ObservableField<String>("")



    fun submit(view:View) {
        if (validated())
            authRepository.userLogin(email.get()!!, password.get()!!)
                .subscribe(object :
                    SubscribeWithModel<ResponseData<UserModel>, UserViewModel>(this) {
                    override fun onSuccess(t: ResponseData<UserModel>) {
                        Logger.log(">>>>"+t.data)
                        navigation.postValue(NavigationCommand.To(UserFragmentDirections.actionUserFragmentToListFragment()))
                    }
                }
                )
    }

    private fun validated(): Boolean {
        hideKeyBordObservable.postValue(true)
        emailError.set("")
        passwordError.set("")
        if (TextUtils.isEmpty(email.get())) {
            emailError.set(application.getString(R.string.blank_email))
            emailError.notifyChange()
            return false
        }
        if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email.get())){
            emailError.set(application.getString(R.string.valid_email))
            emailError.notifyChange()
            return false
        }
        if (TextUtils.isEmpty(password.get())) {
            passwordError.set(application.getString(R.string.blank_password))
            passwordError.notifyChange()
            return false
        }


        return true
    }

    fun getAllList(): LiveData<List<UserModel>> = authRepository.getAll()

}