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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getToDosUseCase: GetToDosUseCase,
    private val addCategoryUseCase: AddToDosUseCase,
    private val deleteToDosUseCase: DeleteToDosUseCase
): ViewModel() {

    private val _categories = mutableStateOf(emptyList<ToDos>())
    val categories = _categories

    private val _loginState = MutableStateFlow(Result)

    init {
        viewModelScope.launch {
            getToDosUseCase().collect {
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

    fun deleteToDos(id: ObjectId) {

    }
}