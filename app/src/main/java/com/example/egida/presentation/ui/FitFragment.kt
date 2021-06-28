package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.activity.MainActivity
import com.example.egida.databinding.FitFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FitFragment : Fragment() {

    companion object {
        fun newInstance() = FitFragment()
        const val TAG = " FitFragment"
    }

    private lateinit var binding: FitFragmentBinding
    private lateinit var viewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).closeDrawer()
//        mainViewModel.closeDrawer()
        Log.d(TAG, " $DAY")
    }

    override fun onResume() {
        super.onResume()

        binding.minusRunning.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textRunning.text = (it.running--).toString()
                        binding.textRunning.text = it.running.toString()
                    }
            }
        }

        binding.plusRunning.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textRunning.text = (it.running++).toString()
                        binding.textRunning.text = it.running.toString()
                    }
            }
        }


        binding.minusBikeRide.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textBikeRide.text = (it.bikeRide--).toString()
                        binding.textBikeRide.text = it.bikeRide.toString()
                    }
            }
        }

        binding.plusBikeRide.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textBikeRide.text = (it.bikeRide++).toString()
                        binding.textBikeRide.text = it.bikeRide.toString()
                    }
            }
        }

        binding.minusSleep.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textSleep.text = (it.sleep--).toString()
                        binding.textSleep.text = it.sleep.toString()
                    }
            }
        }

        binding.plusSleep.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textSleep.text = (it.sleep++).toString()
                        binding.textSleep.text = it.sleep.toString()
                    }
            }
        }

        binding.btnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).openDrawer()
//        mainViewModel.openDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FitFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            viewModel.day
                .collect {
                    binding.textRunning.text = it.running.toString()
                    binding.textBikeRide.text = it.bikeRide.toString()
                    binding.textSleep.text = it.sleep.toString()
                }
        }
    }
}