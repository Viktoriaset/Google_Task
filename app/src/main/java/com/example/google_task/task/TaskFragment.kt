package com.example.google_task.task


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.google_task.R
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskFragment() : Fragment(R.layout.fragment_task) {
    private val viewModel : TaskViewModel by viewModels()
    private val taskListener = TaskListener(this)
    private val taskAdapter = TaskAdapter(taskListener)
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
                taskAdapter.setTasks(tasks)
                binding.recyclerView.adapter = taskAdapter
            }
        }
    }

    companion object{
        const val LIST_ID = "LIST_ID"
    }

}