package com.example.Egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.databinding.RegistrationFragmentBinding
import com.example.Egida.presentation.viewModel.LoginViewModel
import com.example.Egida.utils.replaceFragment
import com.example.Egida.utils.showToast

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
        const val TAG = " registrationFragment"
    }

    private lateinit var mBinding: RegistrationFragmentBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mBinding.registrationFragmentInputName.doAfterTextChanged {
            viewModel.login = it.toString()
            Log.d(TAG, viewModel.login)
        }
        mBinding.registrationFragmentInputUserEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        mBinding.registrationFragmentInputUserPassword.doAfterTextChanged {
            viewModel.password = it.toString()
            Log.d(TAG, viewModel.password)

        }

        mBinding.registrationFragmentInputUserPassword.doAfterTextChanged {
            viewModel.doublePassword = it.toString()
        }

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })


        mBinding.registrationFragmentBtnContinue.setOnClickListener {
            viewModel.addUser()
        }

        mBinding.registrationFragmentBtnBack.setOnClickListener {
            replaceFragment(
                this,
                SingInFragment.newInstance()
            )
        }
    }
}




