package com.practice.todos.domain.usecase

import com.practice.todos.data.repository.AuthRepository
import com.practice.todos.domain.model.Result
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginAnonymouslyUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Result<User>> {
        return authRepository.loginAnonymously()
    }
}