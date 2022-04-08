package com.example.sqlitedb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sqlitedb.ui.activities.MainActivity
import java.lang.IllegalArgumentException

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivity::class.java)) {
            return MainViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}