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
import com.example.Egida.R
import com.example.Egida.databinding.RegistrationFragmentBinding
import com.example.Egida.presentation.viewModel.LoginViewModel
import com.example.Egida.utils.replaceFragment
import com.example.Egida.utils.showToast
import kotlin.properties.Delegates

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
        const val TAG = " registrationFragment"
    }

    private lateinit var mBinding: RegistrationFragmentBinding
    private var fragment = R.layout.registration_fragment
    private var chek by Delegates.notNull<Boolean>()
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
        mBinding.editUserEmail.doAfterTextChanged {
            viewModel.email = it.toString()
            Log.d(TAG, viewModel.email)
        }

        mBinding.editUserPassword.doAfterTextChanged {
            viewModel.password = it.toString()
            Log.d(TAG, viewModel.password)

        }

        mBinding.editDoubleUserPassword.doAfterTextChanged {
            viewModel.doublePassword = it.toString()
        }

        viewModel.toast.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })

//      в этом методе нужно прописать toast, при совпадении паролей, но он не отображается
        mBinding.btnRegistration.setOnClickListener {
            viewModel.addUser()
        }

        mBinding.btnDetails.setOnClickListener {
            replaceFragment(
                requireView(),
                PasswordRecoveryFragment.newInstance()
            )
        }

        mBinding.btnDetails.setOnClickListener {
            replaceFragment(
                requireView(),
                SingInFragment.newInstance()
            )
        }
    }
}




