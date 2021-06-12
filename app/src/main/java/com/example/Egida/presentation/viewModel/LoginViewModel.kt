package com.example.Egida.presentation.viewModel

import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Egida.App
import com.example.Egida.Dependencies
import com.example.Egida.R
import com.example.Egida.domain.entity.User
import com.example.Egida.domain.useCase.UserUseCase
import kotlinx.coroutines.launch


class MainViewModel(
) : ViewModel() {


    private val userUseCase: UserUseCase by lazy { Dependencies.getUserUseCase() }
    var email: String = ""
    var password: String = ""
    var doublePassword: String = ""
    var toast = MutableLiveData<String>()
    var fragment = MutableLiveData<Fragment>()


    fun checkUser(): Boolean {
        return userUseCase.checkUser()
    }

    fun addUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(doublePassword)
        ) {
            if (password == doublePassword) {
                viewModelScope.launch {
                    val user = User(email, password)
                    userUseCase.addUser(user)
                }
            } else {
                toast.value = statesUser(StatesUser.PasswordsDontMatch)
            }
        } else {
            toast.value = statesUser(StatesUser.EmailAndPassword)
        }
    }

    fun singInUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
        ) {
            val user = User(email, password)
            userUseCase.singInUser(user)
        } else {
            toast.value = statesUser(StatesUser.EmailAndPassword)
        }
    }

    fun sendPasswordResetEmail() {
        if (!TextUtils.isEmpty(email)) {
            userUseCase.sendPasswordResetEmail(email)
            toast.value = "Письмо для востановления пороля отправлено на почту"
        } else {
            toast.value = "Проверьте правильность написания электронного адреса"
        }
    }

    fun replaceFragment(view: View, fragment: Fragment) {
        val activity: AppCompatActivity = (view.context as AppCompatActivity?)!!
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null).commit();
    }

    fun statesUser(stateUser: StatesUser) = when (stateUser) {
        is StatesUser.PasswordsDontMatch -> App.instance.resources.getString(R.string.passwords_dont_match)
        is StatesUser.EmailAndPassword -> App.instance.resources.getString(R.string.email_and_password)
    }
}

sealed class StatesUser {
    object PasswordsDontMatch : StatesUser()
    object EmailAndPassword : StatesUser()
}





