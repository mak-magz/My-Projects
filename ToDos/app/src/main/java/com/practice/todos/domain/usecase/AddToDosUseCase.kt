package com.practice.todos.domain.usecase

import android.util.Log
import com.practice.todos.data.local.model.ToDos
import com.practice.todos.data.repository.ToDosRepository
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToDosUseCase @Inject constructor(
    private val repository: ToDosRepository
) {

    suspend operator fun invoke(title: String) {
        return withContext(Dispatchers.IO) {
            val app = App.create("application-0-hqyoc")
            val toDos = ToDos().apply {
                this@apply.title = title
                this@apply.owner_id = app.currentUser?.id ?: ""
            }

            Log.d("TODO ADD", toDos.owner_id)

            repository.addToDos(toDos)
        }
    }
}