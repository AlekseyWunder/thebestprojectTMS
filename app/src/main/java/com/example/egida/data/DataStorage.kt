package com.example.egida.data

import android.net.Uri
import android.util.Log
import com.example.egida.domain.useCase.dataStorage.DataStorageRepository
import com.example.egida.utils.CURRENT_UID
import com.example.egida.utils.initFirebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStorage : DataStorageRepository {

    companion object {
        const val FOlDER_PROFILE_IMAGE = "profile_image"
        const val TAG = "dataStorage  "
    }

    private var REF_STORAGE_ROOT: StorageReference = FirebaseStorage.getInstance().reference
    private var _photoURL = MutableStateFlow(DataStorageState.Success(""))
    override var photoUrl: StateFlow<DataStorageState> = _photoURL
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())


    init {
        initFirebase()
    }

    override fun addProfileImage(uri: Uri) {
        val path = REF_STORAGE_ROOT.child(FOlDER_PROFILE_IMAGE).child(CURRENT_UID)
        path.putFile(uri).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "profile photo - success")
                path.downloadUrl.addOnCompleteListener { URL ->
                    if (URL.isSuccessful) {
                        Log.d(TAG, "URL - success")
                        val url = URL.result.toString()
                        Log.d(TAG, "$url")
                        scope.launch {
                            photoUrl.collect {
                                _photoURL.value = DataStorageState.Success(url)
                            }
                            Log.d(TAG, "URL add flow - success")
                        }
                    }
                }
            }
            Log.d(TAG, "success")
        }
    }
}

sealed class DataStorageState {
    data class Success(val photoUrl: String) : DataStorageState()
}
