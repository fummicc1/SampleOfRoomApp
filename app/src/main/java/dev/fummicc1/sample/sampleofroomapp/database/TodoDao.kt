package dev.fummicc1.sample.sampleofroomapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.fummicc1.sample.sampleofroomapp.entity.Todo

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: Int): Todo?
}