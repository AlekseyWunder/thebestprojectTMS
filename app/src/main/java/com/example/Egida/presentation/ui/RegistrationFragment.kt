package com.example.Egida.presentation.ui

import android.annotation.SuppressLint
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
        const val TAG = " registrationFragment"
    }

    private var fragment = R.layout.registration_fragment
    private var chek by Delegates.notNull<Boolean>()
    private lateinit var viewModel: MainViewModel
    private lateinit var editUserEmail: EditText
    private lateinit var editUserPassword: EditText
    private lateinit var editDoubleUserPassword: EditText
    private lateinit var bDetails: Button
    private lateinit var bLoginIn: Button
    private lateinit var bRegistration: Button

    override fun onStart() {
        super.onStart()
      chek = viewModel.checkingUser()
       if(chek){

       }else{
           fragment = R.layout.registration_fragment
       }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(fragment, container, false)
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

        editDoubleUserPassword.doAfterTextChanged {
            viewModel.doublePassword = it.toString()
        }

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })

//      в этом методе нужно прописать toast, при совпадении паролей, но он не отображается
        bRegistration.setOnClickListener {
            viewModel.addUser()
            viewModel.toast
        }

        bDetails.setOnClickListener {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PasswordRecoveryFragment.newInstance())
                .commitNow()
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
        editUserPassword = view.findViewById(R.id.editUserPassword)
        editDoubleUserPassword = view.findViewById(R.id.editDoubleUserPassword)
        bRegistration = view.findViewById(R.id.bRegistration)
        bDetails = view.findViewById(R.id.bDetails)
        bLoginIn = view.findViewById(R.id.bLoginIN)

    }
}




