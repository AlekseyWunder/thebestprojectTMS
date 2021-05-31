package com.example.Egida.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
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

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = " mainFragment"
    }

    private lateinit var mContext: Context

    private lateinit var viewModel: MainViewModel
    private lateinit var editUserEmail: EditText
    private lateinit var editUserPassword: EditText
    private lateinit var editDoubleUserPassword: EditText
    private lateinit var bSingIN: Button
    private lateinit var bDetails: Button
    private lateinit var bLoginIn: Button
    private lateinit var bRegistrate: Button
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @SuppressLint("ShowToast")
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

        viewModel.getCoincidence().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(),"Пороли не совпадают",Toast.LENGTH_SHORT)
        })


        bSingIN.setOnClickListener {
            viewModel.singInUser()
        }
//      в этом методе нужно прописать toast, при совпадении паролей, но он не отображается
        bRegistrate.setOnClickListener {
        viewModel.checkingPasswords()

        }

        bDetails.setOnClickListener {

        }

        bLoginIn.setOnClickListener {

        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editUserEmail = view.findViewById(R.id.editUserEmail)
        editUserPassword = view.findViewById(R.id.editUserPassword)
        editDoubleUserPassword = view.findViewById(R.id.editDoubleUserPassword)
        bSingIN = view.findViewById(R.id.bSinsIn)
        bRegistrate = view.findViewById(R.id.bRegistrate)
        bDetails = view.findViewById(R.id.bDetails)
        bLoginIn = view.findViewById(R.id.bLoginIN)

    }
}