package com.example.egida.domain.useCase.dataStorage

import android.net.Uri
import com.example.egida.data.DataStorage
import com.example.egida.data.DataStorageState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class DataStorageUsecaseImpl(
    private val dataStorage: DataStorage
) : DataStorageUsecase {
    override fun addProfileImage(uri: Uri) {
        return dataStorage.addProfileImage(uri)
    }

    override var photoUrl: StateFlow<DataStorageState>
        get() = dataStorage.photoUrl
        set(value) {}


}