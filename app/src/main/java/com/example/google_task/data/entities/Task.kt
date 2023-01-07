package com.example.google_task.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlin.collections.List

@Entity(
    tableName = "Tasks",
    foreignKeys = [ForeignKey(entity = List::class,
            parentColumns = ["taskId"],
        childColumns = ["listId"],
        onDelete = CASCADE
    )]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId : Int,

    var listId: Int,

    var TaskText : String

)
