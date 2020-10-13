package dev.fummicc1.sample.sampleofroomapp.screens.add_todo

import android.app.Application
import android.graphics.Bitmap
import android.view.animation.Transformation
import androidx.lifecycle.*
import dev.fummicc1.sample.sampleofroomapp.database.TodoDatabase
import dev.fummicc1.sample.sampleofroomapp.entity.Todo
import dev.fummicc1.sample.sampleofroomapp.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class AddTodoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TodoRepository
    private val _todoTask: MutableLiveData<String> = MutableLiveData()
    private val _todoMemo: MutableLiveData<String> = MutableLiveData()
    // persist image-resourceURL
    private val _todoImage: MutableLiveData<String?> = MutableLiveData()
    private val _completeSaving: MutableLiveData<Unit> = MutableLiveData()
    private val _successfulValidating: MutableLiveData<Boolean> = MutableLiveData()


    // openable variables
    val todoTask: LiveData<String>
        get() = _todoTask

    val todoMemo: LiveData<String>
        get() = _todoMemo

    val todoImage: LiveData<String?>
        get() = _todoImage

    val imageSelected: LiveData<Boolean>
        get() = Transformations.map(todoImage, { image -> image?.isNotEmpty() ?: false })

    val successfulValidating: LiveData<Boolean>
        get() = _successfulValidating

    val completeSaving: LiveData<Unit>
        get() = _completeSaving

    init {
        val dao = TodoDatabase.getInstance(application).todoDao
        repository = TodoRepository(dao)
    }

    private fun validate(task: String): Boolean {
        return task.length > 4
    }

    fun imageSelected(uri: String?) {
        _todoImage.value = uri
    }

    fun saveTodo() {
        val task = _todoTask.value ?: ""

        if (!validate(task)) {
            _successfulValidating.value = false
            return
        }

        val memo = todoTask.value ?: ""
        val imageURI = _todoImage.value ?: ""
        val date = Date()
        val todo = Todo(task, memo, date, imageURI)
        GlobalScope.launch  {
            repository.insert(todo)
            MainScope().launch {
                _completeSaving.value = Unit
            }
        }
    }

    fun taskUpdated(task: String) {
        _todoTask.postValue(task)
    }
}