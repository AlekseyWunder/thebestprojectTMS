package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.activity.MainActivity
import com.example.egida.databinding.FitFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.replaceFragment

class FitFragment : Fragment() {

    companion object {
        fun newInstance() = FitFragment()
        const val TAG = " FitFragment"
    }

    private lateinit var binding: FitFragmentBinding
    private lateinit var viewModel: DayViewModel

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
        Log.d(TAG, " $DAY")
    }

    override fun onResume() {
        super.onResume()
        changeValuesFragmentFields()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FitFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)

        binding.minusRunning.setOnClickListener {
            viewModel.day.running = viewModel.day.running - 1
            binding.textRunning.text = viewModel.day.running.toString()
        }

        binding.plusRunning.setOnClickListener {
            viewModel.day.running = viewModel.day.running + 1
            binding.textRunning.text = viewModel.day.running.toString()
        }

        binding.minusBikeRide.setOnClickListener {
            viewModel.day.bikeRide = viewModel.day.bikeRide - 1
            binding.textBikeRide.text = viewModel.day.bikeRide.toString()
        }

        binding.plusBikeRide.setOnClickListener {
            viewModel.day.bikeRide = viewModel.day.bikeRide + 1
            binding.textBikeRide.text = viewModel.day.bikeRide.toString()
        }

        binding.minusSleep.setOnClickListener {
            viewModel.day.sleep = viewModel.day.sleep - 1
            binding.textSleep.text = viewModel.day.sleep.toString()
        }

        binding.plusSleep.setOnClickListener {
            viewModel.day.sleep = viewModel.day.sleep + 1
            binding.textSleep.text = viewModel.day.sleep.toString()
        }

        binding.btnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }
    }

    private fun changeValuesFragmentFields() {
        binding.textRunning.text = viewModel.day.running.toString()
        binding.textBikeRide.text = viewModel.day.bikeRide.toString()
        binding.textSleep.text = viewModel.day.sleep.toString()
    }
}