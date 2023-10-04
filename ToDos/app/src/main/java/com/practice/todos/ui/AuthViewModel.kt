package com.practice.todos.ui

import androidx.lifecycle.ViewModel
import com.practice.todos.domain.usecase.GetCurrentUserUseCase
import com.practice.todos.domain.usecase.InitializeDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.mongodb.User
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val initializeDBUseCase: InitializeDBUseCase
): ViewModel() {

    fun getCurrentUser(): User? {
        return getCurrentUserUseCase()
    }

    fun initializeDB(user: User): Boolean {
        return initializeDBUseCase(user)
    }
}