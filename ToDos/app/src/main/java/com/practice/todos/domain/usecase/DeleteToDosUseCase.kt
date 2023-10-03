package com.practice.todos.domain.usecase

import com.practice.todos.data.repository.ToDosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId
import java.lang.Exception
import javax.inject.Inject

class DeleteToDosUseCase @Inject constructor(
    private val repository: ToDosRepository,
) {
    suspend operator fun invoke(id: ObjectId): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                repository.deleteToDos(id)
                return@withContext true
            } catch (e: Exception) {
                return@withContext false
            }
        }
    }
}