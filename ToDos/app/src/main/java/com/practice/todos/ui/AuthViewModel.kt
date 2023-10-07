package com.practice.todos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.domain.usecase.GetAuthStateUseCase
import com.practice.todos.domain.usecase.GetCurrentUserUseCase
import com.practice.todos.domain.usecase.InitializeDBUseCase
import com.practice.todos.domain.usecase.LogoutUserUseCase
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
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
): ViewModel() {

    private val _authState = MutableStateFlow(AppState())
    val authState  = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            getAuthStateUseCase().collect { change ->
                when(change) {
                    is LoggedIn -> {
                        Log.e("STATE: ", "LOGGED IN")
                        _authState.value = _authState.value.copy(
                            isLoading = false,
                            isLoggedIn = true
                        )
                    }
                    is LoggedOut -> {
                        Log.e("STATE: ", "LOGGED OUT")

                        _authState.value = _authState.value.copy(
                            isLoading = false,
                            isLoggedIn = false
                        )
                    }
                    is Removed -> {
                        Log.e("STATE: ", "LOGGED OUT REMOVED")

                        _authState.value = _authState.value.copy(
                            isLoading = false,
                            isLoggedIn = false
                        )
                    }
                }
            }
        }
    }

    fun initializeDB(): Boolean {
        return initializeDBUseCase()
    }

    // TODO: Create custom user class
    fun getCurrentUser(): User? {
        return getCurrentUserUseCase()
    }

    fun logout() {
        viewModelScope.launch {
            logoutUserUseCase()
            return@launch
        }
    }
}

data class AppState(
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = true
)