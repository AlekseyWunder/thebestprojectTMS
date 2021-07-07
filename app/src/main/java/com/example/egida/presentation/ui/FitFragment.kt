package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.databinding.FitFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceFragment

class FitFragment : Fragment() {

    companion object {
        fun newInstance() = FitFragment()
        const val TAG = " FitFragment"
    }

    private lateinit var binding: FitFragmentBinding
    private lateinit var dayViewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

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

        binding.textRunning.text = dayViewModel.viewModelDay.running.toString()
        binding.textBikeRide.text = dayViewModel.viewModelDay.bikeRide.toString()
        binding.textSleep.text = dayViewModel.viewModelDay.sleep.toString()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()

        binding.minusRunning.setOnClickListener {
            dayViewModel.minusRunning()
            binding.textRunning.text = dayViewModel.viewModelDay.running.toString()
        }

        binding.plusRunning.setOnClickListener {
            dayViewModel.plusRunning()
            binding.textRunning.text = dayViewModel.viewModelDay.running.toString()
        }

        binding.minusBikeRide.setOnClickListener {
            dayViewModel.minusBikeRide()
            binding.textBikeRide.text = dayViewModel.viewModelDay.bikeRide.toString()
        }

        binding.plusBikeRide.setOnClickListener {
            dayViewModel.plusBikeRide()
            binding.textBikeRide.text = dayViewModel.viewModelDay.bikeRide.toString()
        }

        binding.minusSleep.setOnClickListener {
            dayViewModel.minusSleep()
            binding.textSleep.text = dayViewModel.viewModelDay.sleep.toString()
        }

        binding.plusSleep.setOnClickListener {
            dayViewModel.plusSleep()
            binding.textSleep.text = dayViewModel.viewModelDay.sleep.toString()
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
}
