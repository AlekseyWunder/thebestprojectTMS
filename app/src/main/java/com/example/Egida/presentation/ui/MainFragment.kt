package com.example.Egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.LoginActivity
import com.example.Egida.R
import com.example.Egida.presentation.viewModel.MainViewModel
import com.example.Egida.utils.replaceActivity

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = " mainFragment"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        if (!viewModel.checkUser()) replaceActivity(requireView(), LoginActivity())
    }

    override fun onResume() {
        super.onResume()
        viewModel.initUser()
    }
}