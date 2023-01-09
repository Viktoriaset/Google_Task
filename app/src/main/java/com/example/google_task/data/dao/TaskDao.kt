package com.example.google_task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.google_task.data.entities.TaskEntity

@Dao
interface TaskDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TaskEntity)

    @Update
    fun updateTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM Tasks WHERE taskId = :id")
    fun getByIdTask(id: Int)

    @Query("SELECT * FROM Tasks WHERE listId = :listId")
    fun getAllByList(listId: Int) : List<TaskEntity>

}