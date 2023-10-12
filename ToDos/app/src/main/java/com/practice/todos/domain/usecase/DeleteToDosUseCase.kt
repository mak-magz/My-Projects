package com.practice.todos.domain.usecase

import com.practice.todos.data.repository.ToDosRepository
import com.practice.todos.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteToDosUseCase @Inject constructor(
    private val repository: ToDosRepository,
) {
    suspend operator fun invoke(id: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                repository.deleteToDos(id)
                return@withContext Result.Success(id)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
}