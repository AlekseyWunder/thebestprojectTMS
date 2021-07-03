package com.example.egida.presentation.viewModel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.activity.DrawerController
import com.example.egida.domain.useCase.day.DayUseCase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel : ViewModel() {

    companion object {
        const val TAG = " mainViewModel"
    }

    private val userAuthUseCase: UserAuthUseCase by lazy { Dependencies.authUseCase() }
    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }
    private val dayUseCase: DayUseCase by lazy { Dependencies.dayUseCase() }
    private var toast = MutableLiveData<String>()

    fun checkUser(): Boolean {
        val cUser = userAuthUseCase.getCurrentUser()
        return if (cUser != null) {
            if (!cUser.isEmailVerified) {
                toast.value = "Проверьте вашу почту для подтверждения емэйл адресса"
            }
            true
        } else {
            false
        }
    }

    fun singOutUser() {
        userAuthUseCase.singOutUser()
    }

    fun getUser() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                userDatabaseUseCase.getUser()
                dayUseCase.getDay()
            }
        }
    }

    fun closeDrawer(activity: Activity) {
        if (activity is DrawerController) {
            activity.closeDrawer()
        }
    }

    fun openDrawer(activity: Activity) {
        if (activity is DrawerController) {
            activity.openDrawer()
        }
    }
}
