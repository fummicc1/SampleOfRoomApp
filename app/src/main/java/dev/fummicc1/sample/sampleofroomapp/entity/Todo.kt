package dev.fummicc1.sample.sampleofroomapp.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "todo")
data class Todo(
    val task: String,
    val memo: String,
    val due: Date?,
    val imageResourceURL: String?,
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}