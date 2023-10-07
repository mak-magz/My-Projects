package com.practice.todos.domain.usecase

import android.util.Log
import com.practice.todos.data.repository.AuthRepository
import com.practice.todos.di.Database
import com.practice.todos.di.RealmDB
import io.realm.kotlin.mongodb.User
import javax.inject.Inject

class InitializeDBUseCase @Inject constructor(
    private val realmDB: Database,
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Boolean {
        return try {
            authRepository.getCurrentUser()?.let { realmDB.init(it) }
            true
        } catch (e: Exception) {
            Log.e("REALM INITIALIZATION: ", e.toString())
            false
        }
    }
}