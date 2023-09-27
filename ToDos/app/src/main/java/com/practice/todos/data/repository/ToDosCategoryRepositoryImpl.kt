package com.practice.todos.data.repository

import com.practice.todos.data.local.model.Category
import io.realm.kotlin.Realm
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ToDosCategoryRepositoryImpl @Inject constructor(val realm: Realm) : ToDosCategoryRepository {
    override fun getCategories(): List<String> {
        return listOf("One", "Two")
    }

    override fun getCategoriesAsFlow(): Flow<List<Category>> {
        return realm.query(Category::class).asFlow().map { it.list }
    }
}