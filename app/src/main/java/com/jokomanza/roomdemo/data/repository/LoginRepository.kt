package com.jokomanza.roomdemo.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.jokomanza.roomdemo.data.model.LoginTableModel
import com.jokomanza.roomdemo.data.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginRepository {
    companion object {

        private var loginDatabase: LoginDatabase? = null

        private var loginTableModel: LiveData<LoginTableModel>? = null
        private var loginListTableModel: LiveData<List<LoginTableModel>>? = null

        private fun initializeDB(context: Context) : LoginDatabase {
            return LoginDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            loginDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = LoginTableModel(username, password)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {

            loginDatabase = initializeDB(context)

            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }

        fun getLogins(context: Context) : LiveData<List<LoginTableModel>>? {

            loginDatabase = initializeDB(context)

            loginListTableModel = loginDatabase!!.loginDao().getAllLogin()

            return loginListTableModel
        }

    }

}