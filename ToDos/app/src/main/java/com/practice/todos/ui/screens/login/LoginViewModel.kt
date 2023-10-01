package com.practice.todos.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.data.local.model.ToDos
import com.practice.todos.di.DatabaseModule
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _loggedIn = mutableStateOf(false)
    val isLoggedIn = _loggedIn
    fun login() {
        viewModelScope.launch {
            try {
                val success = DatabaseModule.initRealm()
                Log.d("LOGIN: ", success.toString())
                _loggedIn.value = success
            } catch (e: Exception) {
                Log.e("EXCEPTION: ", e.toString())
            }
        }
    }
}