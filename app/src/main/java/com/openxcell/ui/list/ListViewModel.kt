package com.openxcell.ui.list


import androidx.databinding.ObservableField
import com.openxcell.data.pojo.UserRepo
import com.openxcell.data.repository.AuthRepository
import com.openxcell.ui.base.BaseViewModel
import com.openxcell.utills.NavigationCommand
import com.openxcell.utills.SubscribeWithModel
import javax.inject.Inject

class ListViewModel @Inject constructor(private val authRepository: AuthRepository) :

    BaseViewModel() {


    val onSwipeError = ObservableField<Throwable>()
    val onSwipeSuccess = ObservableField<UserRepo>()
    val errorMessage = ObservableField<String>()
    val progress = ObservableField<Boolean>(true)

    var page = 1


    fun onClickSuperSwipe() {

        navigation.postValue(NavigationCommand.To(ListFragmentDirections.actionListFragmentToSwipeFragment()))

    }

    fun retrieveDataList() {
        getUserByData("" + page)
    }

    private fun getUserByData(pageS: String) {
        authRepository.getUserListByPage(pageS)
            .subscribe(object : SubscribeWithModel<UserRepo, ListViewModel>(this) {
                override fun onSuccess(t: UserRepo) {
                    progress.set(false)
                    page++
                    onSwipeSuccess.set(t)
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    progress.set(false)
                    errorMessage.set("")
                    onSwipeError.set(e)
                }

            })
    }


}