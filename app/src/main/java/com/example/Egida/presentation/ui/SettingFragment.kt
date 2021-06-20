package com.example.Egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.activity.MainActivity
import com.example.Egida.databinding.SettingFragmentBinding
import com.example.Egida.domain.entity.UserDB
import com.example.Egida.presentation.viewModel.SettingViewModel
import com.example.Egida.utils.USER_DB
import com.example.Egida.utils.replaceFragment

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
        const val TAG = " registrationFragment"
    }

    private lateinit var mBinding: SettingFragmentBinding
    private lateinit var viewModel: SettingViewModel

    init {
        USER_DB = UserDB()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onResume() {
        super.onResume()
       // viewModel.initUser()
        mBinding.settingsEditFirstName.setText(USER_DB.firstName)
        mBinding.settingEditLastName.setText(USER_DB.lastName)
        mBinding.settingEditPhoneNumber.setText(USER_DB.phoneNumber)
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SettingFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)

        mBinding.settingsEditFirstName.doAfterTextChanged {
            viewModel.firstName = it.toString()
            Log.d(RegistrationFragment.TAG, viewModel.firstName)
        }
        mBinding.settingEditLastName.doAfterTextChanged {
            viewModel.lastName = it.toString()
            Log.d(RegistrationFragment.TAG, viewModel.lastName)
        }
        mBinding.settingCheckAgreement.setOnClickListener {
            if (mBinding.settingCheckAgreement.isChecked) {
                viewModel.checkAgreement = true
            }
        }
        mBinding.settingEditPhoneNumber.doAfterTextChanged {
            viewModel.phoneNumber = it.toString()
            Log.d(RegistrationFragment.TAG, viewModel.phoneNumber)
        }
        mBinding.settingAddPhoto.setOnClickListener {
            viewModel.photoURL = it.toString()
        }
        mBinding.settingTextHeight.text = viewModel.height.toString()
        mBinding.settingMinusHeight.setOnClickListener {
            viewModel.height = viewModel.height - 1
            mBinding.settingTextHeight.text = viewModel.height.toString()
        }
        mBinding.settingPlusHeight.setOnClickListener {
            viewModel.height = viewModel.height + 1
            mBinding.settingTextHeight.text = viewModel.height.toString()
        }
        mBinding.settingTextWeight.text = viewModel.weight.toString()
        mBinding.settingMinusWeight.setOnClickListener {
            viewModel.weight = viewModel.weight - 1
            mBinding.settingTextWeight.text = viewModel.weight.toString()
        }
        mBinding.settingPlusWeight.setOnClickListener {
            viewModel.weight = viewModel.weight + 1
            mBinding.settingTextWeight.text = viewModel.weight.toString()
        }
        mBinding.settingsFragmentBtnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }


    }

}