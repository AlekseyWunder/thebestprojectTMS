package com.example.Egida.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Egida.Dependencies
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.domain.useCase.UserDBUseCase
import com.example.Egida.domain.useCase.userAUTH.UserAUTHUseCase
import com.example.Egida.utils.USER_DB

class SettingViewModel : ViewModel() {


    private val userAUTHUseCase: UserAUTHUseCase by lazy { Dependencies.userAUTHUseCase() }
    private val userDBUseCase: UserDBUseCase by lazy { Dependencies.userDBUseCase() }
    var firstName: String = ""
    var lastName: String = ""
    var checkAgreement: Boolean = false
    var phoneNumber: String = ""
    var photoURL: String = ""
    var height: Int = 180
    var weight: Int = 80
    var id: String = ""
    var login = ""
    var toast = MutableLiveData<String>()


    fun save() {
        addUSer()
        userDBUseCase.updateUser(USER_DB)
    }

    private fun addUSer() {
        USER_DB = UserDB()
        USER_DB.firstName = firstName
        USER_DB.lastName = lastName
        USER_DB.checkAgreement = checkAgreement
        USER_DB.phoneNumber = phoneNumber
        USER_DB.photoURL = photoURL
        USER_DB.height = height
        USER_DB.weight = weight
        USER_DB.id = id
    }

}