package com.example.google_task.data.dao

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.google_task.data.entities.Task

@Dao
interface TaskDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM Tasks WHERE taskId = :id")
    fun getByIdTask(id: Int)

    @Query("SELECT * FROM Tasks WHERE listId = :listId")
    fun getAllByList(listId: Int)

}