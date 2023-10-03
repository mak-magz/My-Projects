package com.practice.todos.data.repository

import com.practice.todos.data.local.model.ToDos
import com.practice.todos.di.Database
import com.practice.todos.di.RealmDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDosRepositoryImpl @Inject constructor(val realmDB: Database) : ToDosRepository {

    override fun getToDos(): Flow<List<ToDos>> {
        return realmDB.getToDos()
    }

    override suspend fun addToDos(toDos: ToDos) {
        return realmDB.addToDos(toDos)
    }

    override suspend fun deleteToDos(id: String) {
        return realmDB.deleteToDos(id)
    }


}