package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.databinding.NutritionFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect

class NutritionFragment : Fragment() {

    companion object {
        fun newInstance() = NutritionFragment()
        const val TAG = " NutritionFragment"
    }

    private lateinit var binding: NutritionFragmentBinding
    private lateinit var dayViewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        binding.minusMeal.setOnClickListener {
            dayViewModel.minusMeal(binding.textMeal)
        }
        binding.plusMeal.setOnClickListener {
            dayViewModel.plusMeal(binding.textMeal)
        }

        binding.minusWater.setOnClickListener {
            dayViewModel.minusWater(binding.textWater)
        }

        binding.plusWater.setOnClickListener {
            dayViewModel.plusWater(binding.textWater)
        }

        binding.minusAlcohol.setOnClickListener {
            dayViewModel.minusAlcohol(binding.textAlcohol)
        }

        binding.plusAlcohol.setOnClickListener {
            dayViewModel.plusAlcohol(binding.textAlcohol)
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
        binding = NutritionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dayViewModel = ViewModelProvider(this).get(DayViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            dayViewModel.day
                .collect {
                    binding.textMeal.text = it.meal.toString()
                    binding.textWater.text = it.water.toString()
                    binding.textAlcohol.text = it.alcohol.toString()
                }
        }
    }
}