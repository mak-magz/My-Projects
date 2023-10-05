package com.practice.todos.data.repository

import com.practice.todos.di.Auth
import com.practice.todos.domain.model.Result
import io.realm.kotlin.mongodb.AuthenticationChange
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val realmAuth: Auth
): AuthRepository {

    override fun loginAnonymously(): Flow<Result<User>> {
        return realmAuth.loginAnonymous()
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): User? {
        return realmAuth.getCurrentUser()
    }

    override fun getAuthStateAsFlow(): Flow<AuthenticationChange> {
        return realmAuth.getAuthStateAsFlow()
    }
}