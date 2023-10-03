package com.practice.todos.data.repository

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {
    override suspend fun login() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): Any {
        TODO("Not yet implemented")
    }
}