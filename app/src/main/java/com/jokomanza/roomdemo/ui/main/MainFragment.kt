package com.jokomanza.roomdemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.liveData
import com.jokomanza.roomdemo.MainActivity
import com.jokomanza.roomdemo.R
import com.jokomanza.roomdemo.data.model.LoginTableModel
import com.jokomanza.roomdemo.data.room.LoginDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getLoginDetails(requireContext(), "admin")?.observe(viewLifecycleOwner) {
            Log.d("MainFragment", "onActivityCreated: $it")
            when (it) {
                null -> viewModel.insertData(requireContext(), "admin", "admin")
            }
        }
    }

}