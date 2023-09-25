package com.practice.todos.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.todos.domain.usecase.GetToDosCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetToDosCategoriesUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            val cat = useCase.invoke()
            Log.d("CATEGORIES: ", cat.toString())
        }
    }
}