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
import com.example.egida.databinding.SettingFragmentBinding
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.presentation.viewModel.SettingViewModel
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

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
        binding.editFirstName.doAfterTextChanged { editable ->
            lifecycleScope.launch {
                settingViewModel.setUserFirstName(editable)
            }
        }
        binding.editLastName.doAfterTextChanged {
            lifecycleScope.launch {
                settingViewModel.setUserLatName(it)
            }
        }
        binding.checkAgreement.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setCheckAgreement(binding.checkAgreement)
            }
        }
        binding.editPhoneNumber.doAfterTextChanged { editable ->
            lifecycleScope.launch {
                settingViewModel.setPhoneNumber(editable)
            }
        }
        binding.addPhoto.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.setAddPhoto()
            }
        }
        binding.minusHeight.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.minusHeight(binding.textHeight)
            }
        }
        binding.plusHeight.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.plusHeight(binding.textHeight)
            }
        }
        binding.minusWeight.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.minusWeight(binding.textWeight)
            }
        }
        binding.plusWeight.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.plusWeight(binding.textWeight)
            }
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
                binding.editFirstName.setText(it.firstName)
                binding.editLastName.setText(it.lastName)
                binding.checkAgreement.isChecked = it.checkAgreement
                binding.editPhoneNumber.setText(it.phoneNumber)
                binding.textHeight.text = it.height.toString()
                binding.textWeight.text = it.weight.toString()
            }
        }
    }
}
