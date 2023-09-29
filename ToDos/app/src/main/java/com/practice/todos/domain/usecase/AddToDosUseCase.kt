package com.practice.todos.domain.usecase

import com.practice.todos.data.local.model.ToDos
import com.practice.todos.data.repository.ToDosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToDosUseCase @Inject constructor(
    private val repository: ToDosRepository
) {

    suspend operator fun invoke(title: String) {
        return withContext(Dispatchers.IO) {
            val toDos = ToDos().apply {
                this@apply.title = title
            }

            repository.addCategory(toDos)
        }
    }
}