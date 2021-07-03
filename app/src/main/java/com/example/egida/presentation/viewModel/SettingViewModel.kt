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

    suspend fun setUserFirstName(editable: Editable?) {
        userDatabase.collect { userDatabase ->
            userDatabase.firstName = editable.toString()
            Log.d(SettingFragment.TAG, userDatabase.lastName)
        }
    }

    suspend fun setUserLatName(editable: Editable?) {
        userDatabase.collect { userDatabase ->
            userDatabase.lastName = editable.toString()
            Log.d(SettingFragment.TAG, userDatabase.lastName)
        }
    }

    suspend fun setCheckAgreement(checkBox: CheckBox) {
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

    suspend fun setPhoneNumber(editable: Editable?) {
        userDatabase.collect { userDatabase ->
            userDatabase.phoneNumber = editable.toString()
            Log.d(SettingFragment.TAG, userDatabase.phoneNumber)
        }
    }

    suspend fun setAddPhoto() {
        userDatabase
            .collect { userDatabase ->
                userDatabase.photoURL = "it.toString()"
                Log.d(SettingFragment.TAG, userDatabase.photoURL)
            }
    }

    suspend fun minusHeight(textView: TextView) {
        userDatabase.collect {
            it.height--
            textView.text = it.height.toString()
        }
    }

    suspend fun plusHeight(textView: TextView) {
        userDatabase.collect {
            it.height++
            textView.text = it.height.toString()
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

    suspend fun plusWeight(textView: TextView) {
        userDatabase.collect {
            it.weight++
            textView.text = it.weight.toString()
        }
    }

    fun save() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                userDatabaseUseCase.addUser(userDatabase)
            }
            if (result != null) {
                userDatabaseUseCase.updateUser(userDatabase)
            }
//            delay(2000)
            userDatabaseUseCase.updateUser(userDatabase)
        }
    }
}