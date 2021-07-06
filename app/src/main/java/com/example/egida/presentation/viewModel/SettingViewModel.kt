package com.example.egida.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Constants
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {

    companion object {
        const val TAG = "settingViewModel: "
    }

    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }

    var userDatabase: SharedFlow<UserDatabase> = userDatabaseUseCase.databaseUser
        .shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)
    var userFirstName = Constants.firstName
    var userLastName = Constants.lastName
    var check: Boolean = Constants.checkAgreement
    var userPhoneNumber = Constants.phoneNumber
    var userHeight: Int = Constants.height
    var userWeight: Int = Constants.weight

    fun initValue() {
        viewModelScope.launch {
            userDatabase.collect {
                userFirstName = it.firstName
                userLastName = it.lastName
                userPhoneNumber = it.phoneNumber
                check = it.checkAgreement
                userHeight = it.height
                userWeight = it.weight
                Log.d(TAG, "$userFirstName $userLastName $userPhoneNumber $check")
            }
        }
    }

    fun setUserFirstName() {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.firstName = userFirstName
                Log.d(TAG, userDatabase.firstName)
            }
        }
    }

    fun setUserLatName() {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.lastName = userLastName
                Log.d(TAG, userDatabase.lastName)
            }
        }
    }

    fun setCheckAgreement() {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.checkAgreement = check
                Log.d(TAG, userDatabase.checkAgreement.toString())
            }
        }
    }

    fun setPhoneNumber() {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.phoneNumber = userPhoneNumber
                Log.d(TAG, userDatabase.phoneNumber)
            }
        }
    }

    fun minusHeight() {
        userHeight--
        viewModelScope.launch {
            userDatabase.collect {
                it.height = userHeight
                Log.d(TAG, it.height.toString())
            }
        }
    }

    fun plusHeight() {
        viewModelScope.launch {
            userHeight++
            userDatabase.collect {
                it.height = userHeight
                Log.d(TAG, userHeight.toString())
            }
        }
    }

    fun minusWeight() {
        userWeight--
        viewModelScope.launch {
            userDatabase.collect {
                it.weight = userWeight
                Log.d(TAG, userWeight.toString())
            }
        }
    }

    fun plusWeight() {
        viewModelScope.launch {
            userWeight++
            userDatabase.collect {
                it.weight = userWeight
                Log.d(TAG, userWeight.toString())
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            userDatabaseUseCase.updateValueUser()
            userDatabaseUseCase.updateUser()
        }
    }


}