package com.practice.todos.domain.usecase

import com.practice.todos.data.local.model.Category
import com.practice.todos.data.repository.ToDosCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddToDoCategoryUseCase @Inject constructor(
    private val repository: ToDosCategoryRepository
) {

    suspend operator fun invoke(title: String) {
        return withContext(Dispatchers.IO) {
            val category = Category().apply {
                this@apply.title = title
            }

            repository.addCategory(category)
        }
    }
}