package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.LoginActivity
import com.example.egida.R
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = " mainFragment"
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        if (!mainViewModel.checkUser()) replaceActivity(requireView(), LoginActivity())
        mainViewModel.updateHeader(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(2000)
            mainViewModel.updateHeader(requireActivity())
        }
    }


}