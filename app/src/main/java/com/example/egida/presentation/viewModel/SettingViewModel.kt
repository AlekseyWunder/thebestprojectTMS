package com.example.egida.presentation.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Constants
import com.example.egida.Dependencies
import com.example.egida.data.DataStorageState
import com.example.egida.domain.entity.User
import com.example.egida.domain.useCase.dataStorage.DataStorageUsecase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {

    companion object {
        const val TAG = "settingViewModel: "
    }

    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }
    private val dataStorageUsecase: DataStorageUsecase by lazy { Dependencies.dataStorageUsecase() }

    var user: SharedFlow<User> = userDatabaseUseCase.databaseUser
    var userFirstName = Constants.firstName
    var userLastName = Constants.lastName
    var check: Boolean = Constants.checkAgreement
    var userPhoneNumber = Constants.phoneNumber
    var userHeight: Int = Constants.height
    var userWeight: Int = Constants.weight
    private var photoURL: String = Constants.photoURL

    fun initValue() {
        viewModelScope.launch {
            user.collect {
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
            user.collect { userDatabase ->
                userDatabase.firstName = userFirstName
                Log.d(TAG, userDatabase.firstName)
            }
        }
    }

    fun setUserLatName() {
        viewModelScope.launch {
            user.collect { userDatabase ->
                userDatabase.lastName = userLastName
                Log.d(TAG, userDatabase.lastName)
            }
        }
    }

    fun setCheckAgreement() {
        viewModelScope.launch {
            user.collect { userDatabase ->
                userDatabase.checkAgreement = check
                Log.d(TAG, userDatabase.checkAgreement.toString())
            }
        }
    }

    fun setPhoneNumber() {
        viewModelScope.launch {
            user.collect { userDatabase ->
                userDatabase.phoneNumber = userPhoneNumber
                Log.d(TAG, userDatabase.phoneNumber)
            }
        }
    }

    fun minusHeight() {
        userHeight--
        viewModelScope.launch {
            user.collect {
                it.height = userHeight
                Log.d(TAG, it.height.toString())
            }
        }
    }

    fun plusHeight() {
        viewModelScope.launch {
            userHeight++
            user.collect {
                it.height = userHeight
                Log.d(TAG, userHeight.toString())
            }
        }
    }

    fun minusWeight() {
        userWeight--
        viewModelScope.launch {
            user.collect {
                it.weight = userWeight
                Log.d(TAG, userWeight.toString())
            }
        }
    }

    fun plusWeight() {
        viewModelScope.launch {
            userWeight++
            user.collect {
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

    fun addProfileImage(uri: Uri) {
        dataStorageUsecase.addProfileImage(uri)
    }

    fun setAddPhoto() {
        viewModelScope.launch {
            dataStorageUsecase.photoUrl.collect { dataStorageState ->
                when (dataStorageState) {
                    is DataStorageState.Success -> photoURL =
                        dataStorageState.photoUrl
                }
                Log.d(MainViewModel.TAG, "photoURL $photoURL")
            }
        }
        Log.d(MainViewModel.TAG, "photoURL2 $photoURL")
    }

    fun addPhotoURL() {
        userDatabaseUseCase.addPhotoUrl(photoURL)
        Log.d(MainViewModel.TAG, "photoURL3 $photoURL")
    }

}