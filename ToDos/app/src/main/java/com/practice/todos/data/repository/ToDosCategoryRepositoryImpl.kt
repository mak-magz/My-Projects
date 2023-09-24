package com.practice.todos.data.repository

import io.realm.kotlin.Realm

class ToDosCategoryRepositoryImpl(val realm: Realm) : ToDosCategoryRepository {
    override fun getCategories(): List<String> {
        return listOf("One", "Two")
    }
}