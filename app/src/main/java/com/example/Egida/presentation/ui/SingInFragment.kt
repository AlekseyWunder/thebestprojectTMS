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
import com.example.Egida.activity.MainActivity
import com.example.Egida.databinding.SingInFragmentBinding
import com.example.Egida.presentation.viewModel.LoginViewModel
import com.example.Egida.utils.replaceActivity
import com.example.Egida.utils.replaceFragment
import com.example.Egida.utils.showToast

class SingInFragment : Fragment() {

    companion object {
        fun newInstance() = SingInFragment()
        const val TAG = " singInFragment"
    }

    private lateinit var mBinding: SingInFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SingInFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mBinding.singInFragmentInputUserEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        mBinding.singInFragmentInputUserPassword.doAfterTextChanged {
            viewModel.password = it.toString()
            Log.d(TAG, viewModel.password)

        }

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

        mBinding.singInFragmentBtnLogin.setOnClickListener {
            viewModel.singInUser()
            //переделать
          replaceActivity(requireView(), MainActivity())
        }

        mBinding.singInFragmentBtnForgotPassword.setOnClickListener {
            replaceFragment(
                this,
                PasswordRecoveryFragment.newInstance()
            )
        }

        mBinding.singInFragmentBtnCreateAccount.setOnClickListener {
            replaceFragment(
                this,
                RegistrationFragment.newInstance()
            )
        }
    }
}