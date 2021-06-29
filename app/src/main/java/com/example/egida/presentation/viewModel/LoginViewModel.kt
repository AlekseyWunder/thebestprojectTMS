package com.example.egida.presentation.viewModel

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserAuth
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userAUTH.UserAuthUseCase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import com.example.egida.utils.StatesUser
import com.example.egida.utils.statesUser
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val userAuthUseCase: UserAuthUseCase by lazy { Dependencies.authUseCase() }
    private val userDbUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }

    var login: String = ""
    var id: String = ""
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
                    val user = UserAuth(email, password)
                    userAuthUseCase.addUser(user)
//                    val userDB = UserDatabase(id, login)
//                    userDbUseCase.updateUser(userDB)
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
            val user = UserAuth(email, password)
            userAuthUseCase.singInUser(user)
        } else {
            toast.value = statesUser(StatesUser.EmailAndPassword)
        }
    }

    fun sendPasswordResetEmail() {
        if (!TextUtils.isEmpty(email)) {
            userAuthUseCase.sendPasswordResetEmail(email)
            toast.value = "Письмо для востановления пороля отправлено на почту"
        } else {
            toast.value = "Проверьте правильность написания электронного адреса"
        }
    }
}







