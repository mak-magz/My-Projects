package com.practice.todos.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.di.Database
import com.practice.todos.di.RealmDB
import com.practice.todos.domain.model.Result
import com.practice.todos.domain.usecase.LoginAnonymouslyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val realmDB: Database,
    private val loginAnonUseCase: LoginAnonymouslyUseCase
) : ViewModel() {

    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState = _loginUIState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            loginAnonUseCase.invoke().collect {
                when (it) {
                    is Result.Error -> {
                        _loginUIState.update { currentState ->
                            currentState.copy(
                                isLoggingIn = false,
                                loginError = it.exception,
                                currentUser = null
                            )
                        }
                    }

                    Result.Loading -> {
                        _loginUIState.update { currentState ->
                            currentState.copy(
                                isLoggingIn = true,
                                loginError = null,
                                currentUser = null
                            )
                        }
                    }

                    is Result.Success -> {
                        _loginUIState.update { currentState ->
                            currentState.copy(
                                isLoggingIn = false,
                                currentUser = it.data,
                                loginError = null,
                                isLoggedIn = true
                            )
                        }
                    }
                }
            }
        }
    }
}

data class LoginUIState(
    val isLoggingIn: Boolean = false,
    val currentUser: User? = null,
    val isLoggedIn: Boolean = false,
    val loginError: Exception? = null
)