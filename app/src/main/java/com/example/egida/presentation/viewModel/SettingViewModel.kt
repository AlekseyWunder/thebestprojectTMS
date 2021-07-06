package com.example.egida.presentation.viewModel

import android.text.Editable
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.data.DataStorageState
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.dataStorage.DataStorageUsecase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import com.example.egida.presentation.ui.SettingFragment
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {

    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }
    private val dataStorageUsecase: DataStorageUsecase by lazy { Dependencies.dataStorageUsecase() }

    var userDatabase: SharedFlow<UserDatabase> = userDatabaseUseCase.databaseUser
        .shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)


    fun setUserFirstName(editable: Editable?) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.firstName = editable.toString()
                Log.d(SettingFragment.TAG, userDatabase.lastName)
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun setUserLatName(editable: Editable?) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.lastName = editable.toString()
                Log.d(SettingFragment.TAG, userDatabase.lastName)
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun setCheckAgreement(checkBox: CheckBox) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                if (checkBox.isChecked) {
                    userDatabase.checkAgreement = true
                    Log.d(SettingFragment.TAG, userDatabase.checkAgreement.toString())
                } else {
                    userDatabase.checkAgreement = false
                    Log.d(SettingFragment.TAG, userDatabase.checkAgreement.toString())
                }
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun setPhoneNumber(editable: Editable?) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.phoneNumber = editable.toString()
                Log.d(SettingFragment.TAG, userDatabase.phoneNumber)
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun minusHeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.height--
                textView.text = it.height.toString()
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun plusHeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.height++
                textView.text = it.height.toString()
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun minusWeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.weight--
                textView.text = it.weight.toString()
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun plusWeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.weight++
                textView.text = it.weight.toString()
            }
            userDatabaseUseCase.updateValueUser(userDatabase)
        }
    }

    fun save() {
        viewModelScope.launch {
            userDatabaseUseCase.updateValueUser(userDatabase)
            userDatabaseUseCase.updateUser(userDatabase)
        }
    }


}