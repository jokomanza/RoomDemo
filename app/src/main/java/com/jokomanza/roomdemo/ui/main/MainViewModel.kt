package com.jokomanza.roomdemo.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jokomanza.roomdemo.data.model.LoginTableModel
import com.jokomanza.roomdemo.data.repository.LoginRepository

class MainViewModel : ViewModel() {
    var liveDataLogin: LiveData<LoginTableModel>? = null
    var liveListDataLogin: LiveData<List<LoginTableModel>>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

    fun getLogins(context: Context) : LiveData<List<LoginTableModel>>? {
        liveListDataLogin = LoginRepository.getLogins(context)
        return liveListDataLogin
    }

}