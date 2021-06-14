package com.example.Egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.databinding.PasswordRecoveryFragmentBinding
import com.example.Egida.presentation.viewModel.LoginViewModel

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
        mBinding.editUserEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        viewModel.toast.observe(viewLifecycleOwner, {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

        mBinding.btnSendPassword.setOnClickListener {
            viewModel.sendPasswordResetEmail()
        }

        mBinding.btnLoginIN.setOnClickListener {
            viewModel.replaceFragment(
                requireView(),
                SingInFragment.newInstance()
            )
        }
    }
}