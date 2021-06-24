package com.example.egida.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.egida.activity.MainActivity
import com.example.egida.databinding.WorkFragmentBinding
import com.example.egida.presentation.viewModel.DayViewModel
import com.example.egida.utils.DAY
import com.example.egida.utils.replaceFragment

class WorkFragment : Fragment() {

    companion object {
        fun newInstance() = WorkFragment()
        const val TAG = " WorkFragment"
    }

    private lateinit var binding: WorkFragmentBinding
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
        binding = WorkFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DayViewModel::class.java)

        binding.minusValueWork.setOnClickListener {
            viewModel.day.work = viewModel.day.work - 1
            binding.textWork.text = viewModel.day.work.toString()
        }

        binding.plusValueWork.setOnClickListener {
            viewModel.day.work = viewModel.day.work + 1
            binding.textWork.text = viewModel.day.work.toString()
        }

        binding.minusValueLeisure.setOnClickListener {
            viewModel.day.leisure = viewModel.day.leisure - 1
            binding.textLeisure.text = viewModel.day.leisure.toString()
        }

        binding.plusValueLeisure.setOnClickListener {
            viewModel.day.leisure = viewModel.day.leisure + 1
            binding.textLeisure.text = viewModel.day.leisure.toString()
        }

        binding.workBtnSave.setOnClickListener {
            viewModel.save()
            replaceFragment(this, MainFragment.newInstance())
        }
    }

    private fun changeValuesFragmentFields() {
        binding.textLeisure.text = viewModel.day.leisure.toString()
        binding.textWork.text = viewModel.day.work.toString()
    }
}