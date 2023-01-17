package com.example.google_task.task_list

import androidx.lifecycle.ViewModel
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.task.RoomTaskDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val roomTaskListDataSource: RoomTaskListDataSource,
    private val roomTaskDataSource: RoomTaskDataSource
) : ViewModel() {

    var listsLiveData = roomTaskListDataSource.loudAllTaskLists()

    fun insertTask(task: TaskEntity){
        roomTaskDataSource.insertNewTask(task)
    }
}