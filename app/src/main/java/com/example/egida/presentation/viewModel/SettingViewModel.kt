package com.example.egida.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class SettingViewModel : ViewModel() {

    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }

    var userDatabase = userDatabaseUseCase.databaseUser
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = UserDatabase())

    fun save() {
        userDatabaseUseCase.addUser(userDatabase)
        userDatabaseUseCase.updateUser(userDatabase)
    }
}