package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.databinding.PasswordRecoveryFragmentBinding
import com.example.egida.presentation.viewModel.LoginViewModel
import com.example.egida.utils.replaceFragment
import com.example.egida.utils.showToast

class PasswordRecoveryFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordRecoveryFragment()
        const val TAG = " passwordRecoveryFragment"
    }

    private lateinit var mBinding: PasswordRecoveryFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = PasswordRecoveryFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mBinding.passwordRecoveryInputEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        viewModel.toast.observe(viewLifecycleOwner, {
            showToast(it)
        })

        mBinding.btnResetPassword.setOnClickListener {
            viewModel.sendPasswordResetEmail()
        }

        mBinding.passwordRecoveryBtnBack.setOnClickListener {
            replaceFragment(
                this,
                SingInFragment.newInstance()
            )
        }
    }
}