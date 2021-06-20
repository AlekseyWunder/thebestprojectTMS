package com.example.Egida.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Egida.LoginActivity
import com.example.Egida.R
import com.example.Egida.presentation.viewModel.MainViewModel
import com.example.Egida.utils.replaceActivity

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = " mainFragment"
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        if (!viewModel.checkUser()) replaceActivity(requireView(), LoginActivity())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_exit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_to_app -> {
                viewModel.singOutUser()
                replaceActivity(requireView(),LoginActivity())
            }
        }
        return true
    }
}