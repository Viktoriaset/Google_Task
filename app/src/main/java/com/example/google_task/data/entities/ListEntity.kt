package com.example.google_task.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "Lists"
)
data class ListEntity(
    @PrimaryKey
    var listId: UUID = UUID.randomUUID(),

    var listName: String = ""
) : java.io.Serializable
