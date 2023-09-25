package com.practice.todos.data.repository

import io.realm.kotlin.Realm
import javax.inject.Inject

class ToDosCategoryRepositoryImpl @Inject constructor(val realm: Realm) : ToDosCategoryRepository {
    override fun getCategories(): List<String> {
        return listOf("One", "Two")
    }
}