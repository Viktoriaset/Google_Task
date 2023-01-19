package com.example.google_task.task


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.google_task.R
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskFragment() : Fragment(R.layout.fragment_task), TaskListener {
    private val viewModel : TaskViewModel by viewModels()
    private val taskAdapter = TaskAdapter(this)

    private var listId: String = ""
    private lateinit var binding : FragmentTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.takeIf { it.containsKey(LIST_ID)}?.apply {
            val tempListId = getString(LIST_ID)
            tempListId?.let {
                listId = tempListId
            }
        }
        viewModel.setListId(listId)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaskBinding.bind(view)

        binding.apply {
            recyclerView.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        setObserve()
    }

    private fun setObserve(){
        viewModel.tasksLiveData.observe(viewLifecycleOwner) { tasks ->
            tasks?.let{
                updateUi(tasks)
                Log.d("BD return", it.toString())
            }
        }
    }

    private fun updateUi(tasks: List<TaskEntity>){
        taskAdapter.setTasks(tasks)
        binding.recyclerView.adapter = taskAdapter
    }

    override fun updateTask(task: TaskEntity) {
        viewModel.updateTask(task)
    }

    override fun deleteTask(task: TaskEntity) {
        viewModel.deleteTask(task)
    }

    override fun showTaskDescription(task: TaskEntity) {
        Toast.makeText(requireContext(), task.taskDescription, Toast.LENGTH_LONG).show()
    }

    companion object{
        const val LIST_ID = "LIST_ID"
    }

}