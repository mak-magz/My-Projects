package com.practice.todos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.domain.usecase.GetAuthStateUseCase
import com.practice.todos.domain.usecase.GetCurrentUserUseCase
import com.practice.todos.domain.usecase.InitializeDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.LoggedIn
import io.realm.kotlin.mongodb.LoggedOut
import io.realm.kotlin.mongodb.Removed
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val initializeDBUseCase: InitializeDBUseCase,
    private val getAuthStateUseCase: GetAuthStateUseCase
): ViewModel() {

    private val _authState = MutableStateFlow<AuthState<*>>(AuthState.Loading)
    val authState  = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            getAuthStateUseCase().collect { change ->
                when(change) {
                    is LoggedIn -> {
                        Log.e("STATE: ", "LOGGED IN")
                        _authState.value = AuthState.LoggedIn(change.user)
                    }
                    is LoggedOut -> {
                        Log.e("STATE: ", "LOGGED OUT")

                        _authState.value = AuthState.LoggedOut
                    }
                    is Removed -> {
                        Log.e("STATE: ", "LOGGED OUT REMOVED")

                        _authState.value = AuthState.LoggedOut
                    }
                }
            }
        }
    }

    // TODO: Create custom user class
    fun getCurrentUser(): User? {
        return getCurrentUserUseCase()
    }

    fun initializeDB(user: User): Boolean {
        return initializeDBUseCase(user)
    }
}

sealed class AuthState<out T> {
    data class LoggedIn<out T>(val user: T) : AuthState<T>()

    object LoggedOut : AuthState<Nothing>()

    object Loading : AuthState<Nothing>()
}