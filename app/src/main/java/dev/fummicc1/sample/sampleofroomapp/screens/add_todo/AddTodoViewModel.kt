package dev.fummicc1.sample.sampleofroomapp.screens.add_todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.fummicc1.sample.sampleofroomapp.database.TodoDatabase
import dev.fummicc1.sample.sampleofroomapp.repositories.TodoRepository

class AddTodoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepository
    private val _todoTask: MutableLiveData<String> = MutableLiveData()
    private val _todoMemo: MutableLiveData<String> = MutableLiveData()
    // persist image-resourceURL
    private val _todoImage: MutableLiveData<String> = MutableLiveData()

    // openable variables
    val todoTask: LiveData<String>
        get() = _todoTask

    val todoMemo: LiveData<String>
        get() = _todoMemo

    val todoImage: LiveData<String>
        get() = _todoImage

    init {
        val dao = TodoDatabase.getInstance(application).todoDao
        repository = TodoRepository(dao)
    }

    fun saveTodo() {
    }
}