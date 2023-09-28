package com.practice.todos.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.data.local.model.Category
import com.practice.todos.domain.usecase.AddToDoCategoryUseCase
import com.practice.todos.domain.usecase.GetToDosCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetToDosCategoriesUseCase,
    private val addCategoryUseCase: AddToDoCategoryUseCase
): ViewModel() {

    private val _categories = mutableStateOf(emptyList<Category>())
    val categories = _categories

    init {
        viewModelScope.launch {
            useCase.invoke().collect {
                _categories.value = it
                Log.d("CATEGORIES: ", categories.value.toString())
            }
        }
    }

    fun addCategory(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addCategoryUseCase(title)
            } catch (e: Exception) {
                Log.e("EXCEPTION: ", e.toString())
            }
        }
    }
}