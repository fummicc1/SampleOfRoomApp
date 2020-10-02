package dev.fummicc1.sample.sampleofroomapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity()
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo() val task: String,
    @ColumnInfo() val memo: String,
    @ColumnInfo() val due: Date
)