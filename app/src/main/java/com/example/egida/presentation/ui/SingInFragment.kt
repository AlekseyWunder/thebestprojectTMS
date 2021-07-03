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
import com.example.egida.activity.MainActivity
import com.example.egida.databinding.SingInFragmentBinding
import com.example.egida.presentation.viewModel.LoginViewModel
import com.example.egida.utils.replaceActivity
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SingInFragment : Fragment() {

    companion object {
        fun newInstance() = SingInFragment()
        const val TAG = " singInFragment"
    }

    private lateinit var binding: SingInFragmentBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SingInFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.inputUserEmail.doAfterTextChanged {
            loginViewModel.email = it.toString()
            Log.d(TAG, loginViewModel.email)
        }

        binding.inputUserPassword.doAfterTextChanged {
            loginViewModel.password = it.toString()
            Log.d(TAG, loginViewModel.password)

        }

        lifecycleScope.launch {
            loginViewModel.message.collect {
                loginViewModel.messageCollect(this@SingInFragment, it)
            }
        }

        binding.btnContinue.setOnClickListener {
            loginViewModel.singInUser(this)
        }

        binding.btnForgotPassword.setOnClickListener {
            replaceFragment(
                this,
                PasswordRecoveryFragment.newInstance()
            )
        }

        binding.btnCreateAccount.setOnClickListener {
            replaceFragment(
                this,
                RegistrationFragment.newInstance()
            )
        }
    }
}
