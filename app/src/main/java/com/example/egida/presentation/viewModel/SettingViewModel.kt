package com.example.egida.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserDb
import com.example.egida.domain.useCase.UserDbUseCase
import com.example.egida.utils.userDb

class SettingViewModel : ViewModel() {

    private val userDbUseCase: UserDbUseCase by lazy { Dependencies.userDbUseCase() }
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
        userDbUseCase.updateUser(userDb)
    }

    private fun addUSer() {
        userDb = UserDb()
        userDb.firstName = firstName
        userDb.lastName = lastName
        userDb.checkAgreement = checkAgreement
        userDb.phoneNumber = phoneNumber
        userDb.photoURL = photoURL
        userDb.height = height
        userDb.weight = weight
        userDb.id = id
    }

}