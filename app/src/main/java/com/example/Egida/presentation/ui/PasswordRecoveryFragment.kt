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

class PasswordRecoveryFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordRecoveryFragment()
        const val TAG = " passwordRecoveryFragment"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var editUserEmail: EditText
    private lateinit var bLoginIn: Button
    private lateinit var bSendPassword: Button

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

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        bSendPassword.setOnClickListener {
            viewModel.sendPasswordResetEmail()
            viewModel.toast
        }

        bLoginIn.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SingInFragment.newInstance())
                .commitNow()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editUserEmail = view.findViewById(R.id.editUserEmail)
        bSendPassword = view.findViewById(R.id.bSendPassword)
        bLoginIn = view.findViewById(R.id.bLoginIN)

    }
}