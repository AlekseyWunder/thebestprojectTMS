package com.example.egida.domain.useCase.dataStorage

import android.net.Uri
import com.example.egida.data.DataStorageState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DataStorageRepository {

    fun addProfileImage(uri: Uri)
    var photoUrl: StateFlow<DataStorageState>
}