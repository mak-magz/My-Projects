package com.practice.todos.domain.usecase

import com.practice.todos.data.repository.ToDosCategoryRepository
import javax.inject.Inject

class GetToDosCategoriesUseCase @Inject constructor(
    private val repository: ToDosCategoryRepository
) {
    operator fun invoke(): List<String> {
        return repository.getCategories()
    }
}