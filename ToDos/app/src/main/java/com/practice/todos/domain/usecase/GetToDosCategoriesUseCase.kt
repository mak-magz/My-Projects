package com.practice.todos.domain.usecase

import com.practice.todos.data.local.model.Category
import com.practice.todos.data.repository.ToDosCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetToDosCategoriesUseCase @Inject constructor(
    private val repository: ToDosCategoryRepository
) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategoriesAsFlow()
    }
}