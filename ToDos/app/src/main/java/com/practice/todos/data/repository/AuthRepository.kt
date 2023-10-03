package com.practice.todos.data.repository

import io.realm.kotlin.mongodb.App

interface AuthRepository {
    suspend fun login()

    suspend fun logout()

    fun getCurrentUser(): Any
}