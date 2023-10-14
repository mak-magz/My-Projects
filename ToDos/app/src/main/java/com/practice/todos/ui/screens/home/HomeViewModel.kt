package com.practice.todos.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.data.local.model.ToDos
import com.practice.todos.domain.model.Result
import com.practice.todos.domain.usecase.AddToDosUseCase
import com.practice.todos.domain.usecase.DeleteToDosUseCase
import com.practice.todos.domain.usecase.GetToDosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getToDosUseCase: GetToDosUseCase,
    private val addCategoryUseCase: AddToDosUseCase,
    private val deleteToDosUseCase: DeleteToDosUseCase
): ViewModel() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _todos = mutableStateOf(emptyList<ToDos>())
    val todos = _todos

    private val _loginState = MutableStateFlow(Result)

    init {
        viewModelScope.launch {
            getToDosUseCase().collect {
                _todos.value = it
                Log.d("CATEGORIES: ", todos.value.toString())
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

    fun deleteToDos(id: String) {
        viewModelScope.launch(dispatcher) {
            when(val result = deleteToDosUseCase(id)) {
                is Result.Error -> {
                    Log.e("DELETION ERROR: ", result.exception.toString())
                }
                is Result.Success -> {
                    Log.e("DELETION SUCCESS: ", result.data)
                }
            }
        }
    }
}