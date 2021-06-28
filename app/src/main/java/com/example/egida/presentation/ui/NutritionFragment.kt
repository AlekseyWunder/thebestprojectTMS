package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.activity.MainActivity
import com.example.egida.databinding.NutritionFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NutritionFragment : Fragment() {

    companion object {
        fun newInstance() = NutritionFragment()
        const val TAG = " NutritionFragment"
    }

    private lateinit var binding: NutritionFragmentBinding
    private lateinit var viewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).closeDrawer()
//        mainViewModel.closeDrawer()
//        Log.d(FitFragment.TAG, " $DAY")
    }

    override fun onResume() {
        super.onResume()

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
        binding = NutritionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            viewModel.day
                .collect {
                    binding.textMeal.text = it.meal.toString()
                    binding.textWater.text = it.water.toString()
                    binding.textAlcohol.text = it.alcohol.toString()
                }
        }

        binding.minusMeal.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textMeal.text = (it.meal--).toString()
                        binding.textMeal.text = it.meal.toString()
                    }
            }
        }

        binding.plusMeal.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textMeal.text = (it.meal++).toString()
                        binding.textMeal.text = it.meal.toString()
                    }
            }
        }


        binding.minusWater.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textWater.text = (it.water--).toString()
                        binding.textWater.text = it.water.toString()
                    }
            }
        }

        binding.plusWater.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textWater.text = (it.water++).toString()
                        binding.textWater.text = it.water.toString()
                    }
            }
        }

        binding.minusAlcohol.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textAlcohol.text = (it.alcohol--).toString()
                        binding.textAlcohol.text = it.alcohol.toString()
                    }
            }
        }

        binding.plusAlcohol.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textAlcohol.text = (it.alcohol++).toString()
                        binding.textAlcohol.text = it.alcohol.toString()
                    }
            }
        }

        binding.btnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }
    }


}