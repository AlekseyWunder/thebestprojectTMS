package com.example.Egida.presentation.viewModel

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Egida.Dependencies
import com.example.Egida.domain.entity.UserAUTH
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.domain.useCase.UserDBUseCase
import com.example.Egida.domain.useCase.userAUTH.UserAUTHUseCase
import com.example.Egida.utils.StatesUser
import com.example.Egida.utils.statesUser
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val userAUTHUseCase: UserAUTHUseCase by lazy { Dependencies.userAUTHUseCase() }
    private val userDBUseCase:UserDBUseCase by lazy { Dependencies.userDBUseCase()}

    var login: String =""
    var id:String = ""
    var email: String = ""
    var password: String = ""
    var doublePassword: String = ""
    var toast = MutableLiveData<String>()
    var fragment = MutableLiveData<Fragment>()

    fun addUser() {
        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)
            && !TextUtils.isEmpty(doublePassword)
        ) {
            if (password == doublePassword) {
                viewModelScope.launch {
                    val user = UserAUTH(email, password)
                    userAUTHUseCase.addUser(user)
                    val userDB = UserDB(id, login)
                    userDBUseCase.createUser(userDB)
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
            val user = UserAUTH(email, password)
            userAUTHUseCase.singInUser(user)
        } else {
            toast.value = statesUser(StatesUser.EmailAndPassword)
        }
    }

    fun sendPasswordResetEmail() {
        if (!TextUtils.isEmpty(email)) {
            userAUTHUseCase.sendPasswordResetEmail(email)
            toast.value = "Письмо для востановления пороля отправлено на почту"
        } else {
            toast.value = "Проверьте правильность написания электронного адреса"
        }
    }
}







