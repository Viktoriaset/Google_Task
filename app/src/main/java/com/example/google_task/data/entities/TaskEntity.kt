package com.example.google_task.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = "Tasks",
    foreignKeys = [ForeignKey(entity = ListEntity::class,
            parentColumns = ["listId"],
        childColumns = ["listId"],
        onDelete = CASCADE
    )]
)
@Parcelize
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0,

    @NotNull
    var listId: Int,

    var taskText: String,

    var taskDescription: String = "",

    var isCompleted: Boolean = false,

    var isFavorite: Boolean = false,

    var taskCreatedDate: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())

) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(taskCreatedDate)
}
