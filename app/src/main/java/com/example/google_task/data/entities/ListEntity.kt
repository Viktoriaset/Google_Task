package com.example.google_task.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Lists"
)
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    var listId: Int,

    var listName: String
) : java.io.Serializable
