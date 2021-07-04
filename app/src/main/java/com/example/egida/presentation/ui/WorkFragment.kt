package com.example.egida.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.egida.databinding.WorkFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.presentation.viewModel.MainViewModel
import com.example.egida.utils.replaceFragment
import kotlinx.coroutines.flow.collect

class WorkFragment : Fragment() {

    companion object {
        fun newInstance() = WorkFragment()
        const val TAG = " WorkFragment"
    }

    private lateinit var binding: WorkFragmentBinding
    private lateinit var dayViewModel: DayViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onStart() {
        super.onStart()
        mainViewModel.closeDrawer(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        binding.minusValueWork.setOnClickListener {
            dayViewModel.minusValueWork(binding.textWork)
        }

        binding.plusValueWork.setOnClickListener {
            dayViewModel.plusValueWork(binding.textWork)
        }

        binding.minusValueLeisure.setOnClickListener {
            dayViewModel.minusValueLeisure(binding.textLeisure)
        }

        binding.plusValueLeisure.setOnClickListener {
            dayViewModel.plusValueLeisure(binding.textLeisure)
        }

        binding.workBtnSave.setOnClickListener {
            dayViewModel.save()
            replaceFragment(this, MainFragment.newInstance())
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
        binding = WorkFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayViewModel = ViewModelProvider(this).get(DayViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            dayViewModel.day
                .collect {
                    binding.textLeisure.text = it.leisure.toString()
                    binding.textWork.text = it.work.toString()
                }
        }
    }
}
