package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.R
import com.example.egida.databinding.FitFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect

class FitFragment : Fragment(R.layout.fit_fragment) {

    companion object {
        fun newInstance() = FitFragment()
        const val TAG = " FitFragment"
    }

    private lateinit var binding: FitFragmentBinding
    private lateinit var dayViewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()

        binding.minusRunning.setOnClickListener {
            dayViewModel.minusRunning(binding.textRunning)
        }

        binding.plusRunning.setOnClickListener {
            dayViewModel.plusRunning(binding.textRunning)
        }

        binding.minusBikeRide.setOnClickListener {
            dayViewModel.minusBikeRide(binding.textBikeRide)
        }

        binding.plusBikeRide.setOnClickListener {
            dayViewModel.plusBikeRide(binding.textBikeRide)
        }

        binding.minusSleep.setOnClickListener {
            dayViewModel.minusSleep(binding.textSleep)
        }

        binding.plusSleep.setOnClickListener {
            dayViewModel.plusSleep(binding.textSleep)
        }

        binding.btnSave.setOnClickListener {
            dayViewModel.save()
            replaceFragment(requireView(), MainFragment.newInstance())
        }
    }

    override fun onStop() {
        super.onStop()
        mainViewModel.openDrawer(requireActivity())
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
        dayViewModel = ViewModelProvider(this).get(DayViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            dayViewModel.day
                .collect {
                    Log.d(TAG, "$it")
                    binding.textRunning.text = it.running.toString()
                    binding.textBikeRide.text = it.bikeRide.toString()
                    binding.textSleep.text = it.sleep.toString()
                }
        }

    }
}
