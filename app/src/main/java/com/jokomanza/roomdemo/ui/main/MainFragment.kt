package com.jokomanza.roomdemo.ui.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jokomanza.roomdemo.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.time.Instant

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getLogins(requireContext())?.observe(viewLifecycleOwner) {
            Log.d("MainFragment", "onActivityCreated: $it")
            when (it) {
                null -> viewModel.insertData(requireContext(), "admin", "admin")
            }
        }

        message.setOnClickListener {
            viewModel.insertData(requireContext(), Instant.now().toString(), Instant.now().toString())
        }
    }

}