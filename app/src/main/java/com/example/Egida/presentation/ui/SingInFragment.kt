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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.R
import com.example.Egida.presentation.viewModel.MainViewModel

class SingInFragment : Fragment() {

    companion object {
        fun newInstance() = SingInFragment()
        const val TAG = " singInFragment"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var editUserEmail: EditText
    private lateinit var editUserPassword: EditText
    private lateinit var bDetails: Button
    private lateinit var bCreateAccount: Button
    private lateinit var bSingIn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.sing_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        editUserEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        editUserPassword.doAfterTextChanged {
            viewModel.password = it.toString()
            Log.d(TAG, viewModel.password)

        }

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

        bSingIn.setOnClickListener {
            viewModel.singInUser()
        }

        bDetails.setOnClickListener {
            viewModel.replaceFragment(
                requireView(),
                PasswordRecoveryFragment.newInstance()
            )
        }

        bCreateAccount.setOnClickListener {
            viewModel.replaceFragment(
                requireView(),
                RegistrationFragment.newInstance()
            )
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editUserEmail = view.findViewById(R.id.editUserEmail)
        editUserPassword = view.findViewById(R.id.editUserPassword)
        bSingIn = view.findViewById(R.id.bSingIn)
        bDetails = view.findViewById(R.id.bDetails)
        bCreateAccount = view.findViewById(R.id.bCreateAccount)

    }

}