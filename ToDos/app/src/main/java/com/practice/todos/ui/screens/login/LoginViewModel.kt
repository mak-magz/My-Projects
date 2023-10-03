package com.practice.todos.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.di.Database
import com.practice.todos.di.RealmDB
import com.practice.todos.domain.usecase.LoginAnonymouslyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val realmDB: Database,
    private val loginAnonUseCase: LoginAnonymouslyUseCase
): ViewModel() {

    private val _loggedIn = mutableStateOf(false)
    val isLoggedIn = _loggedIn
    fun login() {
        viewModelScope.launch {
            try {
//                val db = RealmDB.getInstance()
                val success = realmDB.init()
                Log.d("LOGIN: ", success.toString())
                _loggedIn.value = success
            } catch (e: Exception) {
                Log.e("EXCEPTION: ", e.toString())
            }
        }
    }
}

sealed class Login