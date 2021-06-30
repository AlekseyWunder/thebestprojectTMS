package com.example.egida.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egida.Dependencies
import com.example.egida.domain.entity.UserDatabase
import com.example.egida.domain.useCase.userDatabase.UserDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel : ViewModel() {

    private val userDatabaseUseCase: UserDatabaseUseCase by lazy { Dependencies.userDatabaseUseCase() }

    var userDatabase: StateFlow<UserDatabase> = userDatabaseUseCase.databaseUser
        .stateIn(viewModelScope, started = SharingStarted.Lazily, initialValue = UserDatabase())


    suspend fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                userDatabaseUseCase.addUser(userDatabase)
            }
            userDatabaseUseCase.updateUser(userDatabase)
        }
    }
}