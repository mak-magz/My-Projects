package com.practice.todos.domain.usecase

import android.util.Log
import com.practice.todos.di.Database
import com.practice.todos.di.RealmDB
import io.realm.kotlin.mongodb.User
import javax.inject.Inject

class InitializeDBUseCase @Inject constructor(
    private val realmDB: Database
) {
    operator fun invoke(user: User): Boolean {
        return try {
            realmDB.init(user)
            true
        } catch (e: Exception) {
            Log.e("REALM INITIALIZATION: ", e.toString())
            false
        }
    }
}