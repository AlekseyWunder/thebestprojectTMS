package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.activity.MainActivity
import com.example.egida.databinding.NutritionFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.replaceFragment

class NutritionFragment : Fragment() {

    companion object {
        fun newInstance() = NutritionFragment()
        const val TAG = " NutritionFragment"
    }

    private lateinit var binding: NutritionFragmentBinding
    private lateinit var viewModel: DayViewModel

    override fun onStart() {
        super.onStart()
//        (activity as MainActivity).mAppDrawer.disableDrawer()
        Log.d(TAG, " $DAY")
    }

    override fun onResume() {
        super.onResume()
        changeValuesFragmentFields()
    }

    override fun onStop() {
        super.onStop()
//        (activity as MainActivity).mAppDrawer.enableDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NutritionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)

        binding.minusMeal.setOnClickListener {
            viewModel.day.meal = viewModel.day.meal - 1
            binding.textMeal.text = viewModel.day.meal.toString()
        }

        binding.plusMeal.setOnClickListener {
            viewModel.day.meal = viewModel.day.meal + 1
            binding.textMeal.text = viewModel.day.meal.toString()
        }

        binding.minusWater.setOnClickListener {
            viewModel.day.water = viewModel.day.water - 1
            binding.textWater.text = viewModel.day.water.toString()
        }

        binding.plusWater.setOnClickListener {
            viewModel.day.water = viewModel.day.water + 1
            binding.textWater.text = viewModel.day.water.toString()
        }

        binding.minusAlcohol.setOnClickListener {
            viewModel.day.alcohol = viewModel.day.alcohol - 1
            binding.textAlcohol.text = viewModel.day.alcohol.toString()
        }

        binding.plusAlcohol.setOnClickListener {
            viewModel.day.alcohol = viewModel.day.alcohol + 1
            binding.textAlcohol.text = viewModel.day.alcohol.toString()
        }

        binding.btnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }
    }

    private fun changeValuesFragmentFields() {
        binding.textAlcohol.text = viewModel.day.alcohol.toString()
        binding.textWater.text = viewModel.day.water.toString()
        binding.textMeal.text = viewModel.day.meal.toString()
    }
}