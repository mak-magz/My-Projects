package com.practice.todos.data.repository

import android.util.Log
import com.practice.todos.data.local.model.ToDos
import com.practice.todos.di.DatabaseModule
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

class ToDosRepositoryImpl @Inject constructor(val realm: Realm) : ToDosRepository {
    override fun getCategories(): List<String> {
        return listOf("One", "Two")
    }

    override fun getCategoriesAsFlow(): Flow<List<ToDos>> {
        return realm.query(ToDos::class).asFlow().map { it.list }
    }

    override suspend fun addCategory(toDos: ToDos) {
        realm.write { copyToRealm(toDos)}
    }

    override suspend fun deleteCategory(id: ObjectId) {
        realm.write {
            val person = query<ToDos>(query = "_id == $0", id).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }
}