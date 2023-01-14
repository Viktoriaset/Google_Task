package com.example.google_task.task_list

import androidx.lifecycle.LiveData
import com.example.google_task.data.entities.ListEntity
import kotlinx.coroutines.flow.Flow

interface TaskListDataSource {

    fun loudAllTaskLists() : LiveData<List<ListEntity>>

    fun insertNewTaskList(listEntity: ListEntity)

    fun updateTaskList(listEntity: ListEntity)

    fun deleteTaskList(listEntity: ListEntity)
}