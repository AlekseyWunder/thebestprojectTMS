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
import com.example.egida.databinding.PasswordRecoveryFragmentBinding
import com.example.egida.presentation.viewModel.LoginViewModel
import com.example.egida.utils.replaceFragment
import com.example.egida.utils.showToast
import kotlinx.coroutines.flow.collect

class PasswordRecoveryFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordRecoveryFragment()
        const val TAG = " passwordRecoveryFragment"
    }

    private lateinit var binding: PasswordRecoveryFragmentBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PasswordRecoveryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.inputEmail.doAfterTextChanged {
            loginViewModel.email = it.toString()
            Log.d(TAG, loginViewModel.email)
        }
        lifecycleScope.launchWhenStarted {
            loginViewModel.message.collect {
                showToast(it)
            }
        }

        binding.btnResetPassword.setOnClickListener {
            loginViewModel.sendPasswordResetEmail(this)
        }

        binding.btnBack.setOnClickListener {
            replaceFragment(
                requireView(),
                SingInFragment.newInstance()
            )
        }
    }
}