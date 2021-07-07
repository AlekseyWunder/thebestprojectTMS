package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.databinding.NutritionFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceFragment

class NutritionFragment : Fragment() {

    companion object {
        fun newInstance() = NutritionFragment()
        const val TAG = " NutritionFragment"
    }

    private lateinit var binding: NutritionFragmentBinding
    private lateinit var dayViewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

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

        binding.textMeal.text = dayViewModel.viewModelDay.meal.toString()
        binding.textWater.text = dayViewModel.viewModelDay.water.toString()
        binding.textAlcohol.text = dayViewModel.viewModelDay.alcohol.toString()

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        binding.minusMeal.setOnClickListener {
            dayViewModel.minusMeal()
        }
        binding.plusMeal.setOnClickListener {
            dayViewModel.plusMeal()
        }

        binding.minusWater.setOnClickListener {
            dayViewModel.minusWater()
        }

        binding.plusWater.setOnClickListener {
            dayViewModel.plusWater()
        }

        binding.minusAlcohol.setOnClickListener {
            dayViewModel.minusAlcohol()
        }

        binding.plusAlcohol.setOnClickListener {
            dayViewModel.plusAlcohol()
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