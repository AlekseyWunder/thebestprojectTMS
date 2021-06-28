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
import com.example.egida.databinding.WorkFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WorkFragment : Fragment() {

    companion object {
        fun newInstance() = WorkFragment()
        const val TAG = " WorkFragment"
    }

    private lateinit var binding: WorkFragmentBinding
    private lateinit var viewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).closeDrawer()
//        mainViewModel.closeDrawer()
        Log.d(FitFragment.TAG, " $DAY")
    }

    override fun onResume() {
        super.onResume()
//        changeValuesFragmentFields()
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
        binding = WorkFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            viewModel.day
                .collect {
                binding.textLeisure.text = it.leisure.toString()
                binding.textWork.text = it.work.toString()
            }
        }

        binding.minusValueWork.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textWork.text = (it.work--).toString()
                        binding.textWork.text = it.work.toString()
                    }
            }
        }

        binding.plusValueWork.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textWork.text = (it.work++).toString()
                        binding.textWork.text = it.work.toString()
                    }
            }
        }

        binding.minusValueLeisure.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textLeisure.text = (it.leisure--).toString()
                        binding.textLeisure.text = it.leisure.toString()
                    }
            }
        }

        binding.plusValueLeisure.setOnClickListener {
            lifecycleScope.launch {
                viewModel.day
                    .collect {
                        binding.textLeisure.text = (it.leisure++).toString()
                        binding.textLeisure.text = it.leisure.toString()
                    }
            }
        }

        binding.workBtnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }
    }


}