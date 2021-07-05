package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.R
import com.example.egida.databinding.SettingFragmentBinding
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.presentation.viewModel.SettingViewModel
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect

class SettingFragment : Fragment(R.layout.setting_fragment) {

    companion object {
        fun newInstance() = SettingFragment()
        const val TAG = " SettingFragment"
    }

    private lateinit var binding: SettingFragmentBinding
    private lateinit var settingViewModel: SettingViewModel
    private lateinit var mainViewModel: MainViewModel


    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        binding.settingsEditFirstName.doAfterTextChanged { editable ->
            settingViewModel.setUserFirstName(editable)
        }
        binding.settingEditLastName.doAfterTextChanged {
            settingViewModel.setUserLatName(it)
        }
        binding.settingCheckAgreement.setOnClickListener {
            settingViewModel.setCheckAgreement(binding.settingCheckAgreement)
        }
        binding.settingEditPhoneNumber.doAfterTextChanged { editable ->
            settingViewModel.setPhoneNumber(editable)
        }
        binding.settingAddPhoto.setOnClickListener {
            settingViewModel.setAddPhoto()

        }
        binding.settingMinusHeight.setOnClickListener {
            settingViewModel.minusHeight(binding.settingTextHeight)
        }
        binding.settingPlusHeight.setOnClickListener {
            settingViewModel.plusHeight(binding.settingTextHeight)
        }
        binding.settingMinusWeight.setOnClickListener {
            settingViewModel.minusWeight(binding.settingTextWeight)
        }
        binding.settingPlusWeight.setOnClickListener {
            settingViewModel.plusWeight(binding.settingTextWeight)
        }

        binding.settingsFragmentBtnSave.setOnClickListener {
            settingViewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }

    }

    override fun onStop() {
        super.onStop()
        mainViewModel.openDrawer(requireActivity())
    }

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

        lifecycleScope.launchWhenStarted {
            settingViewModel.userDatabase.collect {
                Log.d(FitFragment.TAG, "$it")
                binding.settingsEditFirstName.setText(it.firstName)
                binding.settingEditLastName.setText(it.lastName)
                binding.settingCheckAgreement.isChecked = it.checkAgreement
                binding.settingEditPhoneNumber.setText(it.phoneNumber)
                binding.settingTextHeight.text = it.height.toString()
                binding.settingTextWeight.text = it.weight.toString()
            }
        }
    }
}
