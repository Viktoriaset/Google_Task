package com.example.google_task.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.google_task.data.entities.TaskEntity
import java.util.concurrent.Flow

@Dao
interface TaskDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertTask(task: TaskEntity)

    @Update
    fun updateTask(task: TaskEntity)

    @Delete
     fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM Tasks WHERE listId = :listId")
    fun getAllTasksByList(listId: Int) : LiveData<List<TaskEntity>>

    @Query("SELECT * FROM Tasks")
    fun getAllTasks() : LiveData<List<TaskEntity>>

}