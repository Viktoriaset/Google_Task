package com.example.google_task.task

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.google_task.data.dao.TaskDao
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject

class RoomTaskDataSource @Inject constructor(
    private val taskDao: TaskDao
    ) : TaskDataSource {

    private val executor =  Executors.newSingleThreadExecutor()

    override fun loudAllTasks(): LiveData<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    override fun loudAllTaskByTaskList(listId: String): LiveData<List<TaskEntity>> {
        return taskDao.getAllTasksByList(UUID.fromString(listId))
    }

    override fun loudTasksFavourite(): LiveData<List<TaskEntity>> {
        return taskDao.getFavouriteTasks()
    }

    override fun insertNewTask(task: TaskEntity) {

        executor.execute {
            taskDao.insertTask(task)
        }

    }

    override  fun updateTask(task: TaskEntity) {
        Log.d("tag", task.toString())
        executor.execute{
             taskDao.updateTask(task)
        }
    }

    override fun deleteTask(task: TaskEntity) {
        executor.execute{
            taskDao.deleteTask(task)
        }
    }

}