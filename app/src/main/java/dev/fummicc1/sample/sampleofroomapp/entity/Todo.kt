package dev.fummicc1.sample.sampleofroomapp.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val task: String,
    val memo: String,
    val due: Date?,
    val imageResourceURL: String?,
)