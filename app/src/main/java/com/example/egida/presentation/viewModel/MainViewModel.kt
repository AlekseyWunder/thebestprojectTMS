package com.example.egida.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.egida.Dependencies
import com.example.egida.domain.useCase.UserDbUseCase
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase


class MainViewModel : ViewModel() {

    companion object {
        const val TAG = " mainViewModel"
    }

    private val userAuthUseCase: UserAuthUseCase by lazy { Dependencies.authUseCase() }
    private val userDbUseCase: UserDbUseCase by lazy { Dependencies.userDbUseCase() }
    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }

    var toast = MutableLiveData<String>()

    fun checkUser(): Boolean {
//        var cUser = userAuthUseCase.getCurrentUser()
//        return if (cUser != null) {
//            if (!cUser.isEmailVerified) {
//                toast.value = "Проверьте вашу почту для подтверждения емэйл адресса"
//            }
//            true
//        } else {
//            false
//        }
        return true
    }

    fun singOutUser() {
        userAuthUseCase.singOutUser()
    }

    fun getUser() {
        userDbUseCase.getUser()
        dayUseCase.getDay()
    }

//    fun disableDrawer(activity:AppCompatActivity) {
//        (activity as MainActivity).mAppDrawer.disableDrawer()
//    }
}