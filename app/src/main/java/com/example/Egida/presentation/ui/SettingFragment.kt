package com.example.Egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.activity.MainActivity
import com.example.Egida.databinding.SettingFragmentBinding
import com.example.Egida.presentation.viewModel.MainViewModel

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
        const val TAG = " registrationFragment"
    }
    private lateinit var mBinding: SettingFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SettingFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}