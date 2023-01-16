package com.example.google_task.task_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val roomTaskListDataSource: RoomTaskListDataSource
) : ViewModel() {

    var listsLiveData = roomTaskListDataSource.loudAllTaskLists()
}