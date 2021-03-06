package com.example.egida.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.appActivity
import com.example.egida.databinding.SettingFragmentBinding
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.presentation.viewModel.SettingViewModel
import com.example.egida.utils.replaceFragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
        const val TAG = " SettingFragment"
    }

    private lateinit var binding: SettingFragmentBinding
    private lateinit var settingViewModel: SettingViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingViewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        settingViewModel.initValue()
        binding.editFirstName.setText(settingViewModel.userFirstName)
        binding.editLastName.setText(settingViewModel.userLastName)
        binding.checkAgreement.isChecked = settingViewModel.check
        binding.editPhoneNumber.setText(settingViewModel.userPhoneNumber)
        binding.textHeight.text = settingViewModel.userHeight.toString()
        binding.textWeight.text = settingViewModel.userWeight.toString()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        binding.editFirstName.doAfterTextChanged {
            settingViewModel.userFirstName = it.toString()
            settingViewModel.setUserFirstName()
        }
        binding.editLastName.doAfterTextChanged {
            settingViewModel.userLastName = it.toString()
            settingViewModel.setUserLatName()
        }
        binding.checkAgreement.setOnClickListener {
            if (binding.checkAgreement.isChecked) {
                settingViewModel.check = true
                settingViewModel.setCheckAgreement()
            } else {
                settingViewModel.check = false
                settingViewModel.setCheckAgreement()
            }
        }
        binding.editPhoneNumber.doAfterTextChanged {
            settingViewModel.userPhoneNumber = it.toString()
            settingViewModel.setPhoneNumber()
        }
        binding.addPhoto.setOnClickListener {
            changePhotoUser()
        }
        binding.minusHeight.setOnClickListener {
            settingViewModel.minusHeight()
            binding.textHeight.text = settingViewModel.userHeight.toString()
        }
        binding.plusHeight.setOnClickListener {
            settingViewModel.plusHeight()
            binding.textHeight.text = settingViewModel.userHeight.toString()
        }
        binding.minusWeight.setOnClickListener {
            settingViewModel.minusWeight()
            binding.textWeight.text = settingViewModel.userWeight.toString()
        }
        binding.plusWeight.setOnClickListener {
            settingViewModel.plusWeight()
            binding.textWeight.text = settingViewModel.userWeight.toString()
        }

        binding.settingsFragmentBtnSave.setOnClickListener {
            settingViewModel.save()
            replaceFragment(requireView(), MainFragment.newInstance())
        }
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.openDrawer(requireActivity())
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(appActivity, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == AppCompatActivity.RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            lifecycleScope.launch {
                withContext(Dispatchers.Default) {
                    settingViewModel.addProfileImage(uri)
                }
                settingViewModel.setAddPhoto()
                delay(2000)
                settingViewModel.addPhotoURL()
                mainViewModel.updateHeader(requireActivity())
            }
        }
    }


}
