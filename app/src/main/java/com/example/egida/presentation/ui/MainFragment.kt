package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.LoginActivity
import com.example.egida.R
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceActivity
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = " mainFragment"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)

        val fragmentName = arguments?.getString("fragmentName")

        rootView.score.text = fragmentName

        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        if (!viewModel.checkUser()) replaceActivity(requireView(), LoginActivity())
    }


}