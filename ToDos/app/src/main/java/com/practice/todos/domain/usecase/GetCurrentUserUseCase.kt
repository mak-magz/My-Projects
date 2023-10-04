package com.practice.todos.domain.usecase

import com.practice.todos.data.repository.AuthRepository
import io.realm.kotlin.mongodb.User
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): User? {
        return authRepository.getCurrentUser()
    }
}