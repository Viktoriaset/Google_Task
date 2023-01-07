package com.example.google_task.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.google_task.data.dao.ListDao
import com.example.google_task.data.dao.TaskDao
import com.example.google_task.data.entities.Task

@Database(entities = [
        List::class,
        Task::class],
    version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    public abstract fun listDao() : ListDao
    public abstract fun taskDao() : TaskDao
}