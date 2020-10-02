package dev.fummicc1.sample.sampleofroomapp.repositories

import androidx.lifecycle.LiveData
import dev.fummicc1.sample.sampleofroomapp.database.TodoDao
import dev.fummicc1.sample.sampleofroomapp.entity.Todo

class TodoRepository(private val dao: TodoDao) {
    val allWords: LiveData<List<Todo>> = dao.getAll()

    // The suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
    suspend fun insert(todo: Todo) {
        dao.insert(todo)
    }
}