package com.example.Egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.R
import com.example.Egida.presentation.viewModel.MainViewModel

class PasswordRecoveryFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordRecoveryFragment()
        const val TAG = " passwordRecoveryFragment"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var editUserEmail: EditText
    private lateinit var btnLoginIn: Button
    private lateinit var btnSendPassword: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.password_recovery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        editUserEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        viewModel.toast.observe(viewLifecycleOwner, {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

        btnSendPassword.setOnClickListener {
            viewModel.sendPasswordResetEmail()
        }

        btnLoginIn.setOnClickListener {
            viewModel.replaceFragment(
                requireView(),
                SingInFragment.newInstance()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editUserEmail = view.findViewById(R.id.editUserEmail)
        btnSendPassword = view.findViewById(R.id.bSendPassword)
        btnLoginIn = view.findViewById(R.id.bLoginIN)

    }

}