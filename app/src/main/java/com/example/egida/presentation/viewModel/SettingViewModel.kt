package com.example.egida.presentation.viewModel

import android.text.Editable
import android.util.Log
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import com.example.egida.presentation.ui.SettingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel : ViewModel() {

    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }

    var userDatabase: SharedFlow<UserDatabase> = userDatabaseUseCase.databaseUser
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = UserDatabase())

    fun setUserFirstName(editable: Editable?) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.firstName = editable.toString()
                Log.d(SettingFragment.TAG, userDatabase.lastName)
            }
        }
    }

    fun setUserLatName(editable: Editable?) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.lastName = editable.toString()
                Log.d(SettingFragment.TAG, userDatabase.lastName)
            }
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
        }
    }

    fun setPhoneNumber(editable: Editable?) {
        viewModelScope.launch {
            userDatabase.collect { userDatabase ->
                userDatabase.phoneNumber = editable.toString()
                Log.d(SettingFragment.TAG, userDatabase.phoneNumber)
            }
        }
    }

    fun setAddPhoto() {
        viewModelScope.launch {
            userDatabase
                .collect { userDatabase ->
                    userDatabase.photoURL = "it.toString()"
                    Log.d(SettingFragment.TAG, userDatabase.photoURL)
                }
        }
    }

    fun minusHeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.height--
                textView.text = it.height.toString()
            }
        }
    }

    fun plusHeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.height++
                textView.text = it.height.toString()
            }
        }
    }

    fun minusWeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.weight--
                textView.text = it.weight.toString()
            }
        }
    }

    fun plusWeight(textView: TextView) {
        viewModelScope.launch {
            userDatabase.collect {
                it.weight++
                textView.text = it.weight.toString()
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                userDatabaseUseCase.addUser(userDatabase)
            }

            delay(2000)
            userDatabaseUseCase.updateUser(userDatabase)
        }
    }
}