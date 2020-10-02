package dev.fummicc1.sample.sampleofroomapp.database

import androidx.room.*
import dev.fummicc1.sample.sampleofroomapp.entity.Todo

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("select * from todo")
    fun getAll(): List<Todo>

    @Query("select * from todo where id = :id")
    fun getTodo(id: Int): Todo?
}