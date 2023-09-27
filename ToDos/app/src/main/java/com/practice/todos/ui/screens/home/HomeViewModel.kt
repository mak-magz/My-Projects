package com.practice.todos.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.domain.usecase.AddToDoCategoryUseCase
import com.practice.todos.domain.usecase.GetToDosCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetToDosCategoriesUseCase,
    private val addCategoryUseCase: AddToDoCategoryUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            val cat = useCase.invoke().collect {
                Log.d("CATEGORIES: ", it.toString())
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