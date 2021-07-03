package com.example.egida.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.databinding.RegistrationFragmentBinding
import com.example.egida.presentation.viewModel.LoginViewModel
import com.example.egida.utils.replaceFragment
import com.example.egida.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class RegistrationFragment() : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
        const val TAG = " registrationFragment"
    }

    private lateinit var binding: RegistrationFragmentBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistrationFragmentBinding.inflate(inflater, container, false)
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

        binding.inputDoubleUserPassword.doAfterTextChanged {
            loginViewModel.doublePassword = it.toString()
        }
        lifecycleScope.launchWhenStarted {
            loginViewModel.message.collect {
                messageCollect(it)
            }
            loginViewModel.messageDatabase.collect {
                messageCollect(it)
            }
            loginViewModel.errorMessage.collect {
                messageCollect(it)
            }
        }

        binding.btnContinue.setOnClickListener {
            loginViewModel.addUser()
        }
        binding.registrationFragmentBtnBack.setOnClickListener {
            replaceFragment(
                this,
                SingInFragment.newInstance()
            )
        }
    }

    private suspend fun messageCollect(message: String) {
        withContext(Dispatchers.Default) {
            Handler(Looper.getMainLooper()).post {
                showToast(message)
            }
        }
    }
}



