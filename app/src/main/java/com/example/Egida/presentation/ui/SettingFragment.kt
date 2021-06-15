package com.example.Egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Egida.R

class SettingFragment : Fragment() {
    companion object {
        fun newInstance() = SettingFragment()
        const val TAG = " settingFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }
}