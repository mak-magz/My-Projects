package com.practice.todos.domain.usecase

import com.practice.todos.data.repository.AuthRepository
import io.realm.kotlin.mongodb.AuthenticationChange
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<AuthenticationChange> {
        return authRepository.getAuthStateAsFlow()
    }
}