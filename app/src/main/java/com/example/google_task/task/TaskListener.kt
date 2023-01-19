package com.example.google_task.task

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.google_task.R
import com.example.google_task.data.entities.TaskEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListener(private val fragment: Fragment) : Fragment(), TaskListenerInterface {

    private val viewModel : TaskViewModel by viewModels()

    override fun updateTask(task: TaskEntity) {
        viewModel.updateTask(task)
    }

    override fun deleteTask(task: TaskEntity) {
        Toast.makeText(fragment.requireContext(), fragment.getString(R.string.completed_task), Toast.LENGTH_LONG).show()
        viewModel.deleteTask(task)
    }

    override fun showTaskDescription(task: TaskEntity) {
        Toast.makeText(fragment.requireContext(), task.taskDescription, Toast.LENGTH_LONG).show()
    }
}