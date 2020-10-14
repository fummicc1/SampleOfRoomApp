package dev.fummicc1.sample.sampleofroomapp.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fummicc1.sample.sampleofroomapp.database.TodoDatabase
import dev.fummicc1.sample.sampleofroomapp.entity.Todo
import dev.fummicc1.sample.sampleofroomapp.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepository

    // Using LiveData and caching what getAll returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val todos: LiveData<List<Todo>>

    init {
        val todoDao = TodoDatabase.getInstance(application).todoDao
        repository = TodoRepository(todoDao)
        todos = repository.allWords
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.delete(todo)
        }
    }
}