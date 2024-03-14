package com.example.room_with_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PyramidViewModel(private val repository: PyramidRepository) : ViewModel() {

    val allPyramids: LiveData<List<Pyramid>> = repository.allPyramids.asLiveData()

    fun insert(pyramid: Pyramid) = viewModelScope.launch {
        repository.insert(pyramid)
    }
}

class PyramidViewModelFactory(private val repository: PyramidRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PyramidViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PyramidViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}