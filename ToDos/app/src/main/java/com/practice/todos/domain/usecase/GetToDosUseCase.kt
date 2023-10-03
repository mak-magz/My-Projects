package com.practice.todos.domain.usecase

import com.practice.todos.data.local.model.ToDos
import com.practice.todos.data.repository.ToDosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToDosUseCase @Inject constructor(
    private val repository: ToDosRepository
) {
    operator fun invoke(): Flow<List<ToDos>> {
        return repository.getToDos()
    }
}