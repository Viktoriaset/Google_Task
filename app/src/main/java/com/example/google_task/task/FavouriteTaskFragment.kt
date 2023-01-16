package com.example.google_task.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.google_task.R
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteTaskFragment : Fragment(R.layout.fragment_task), TaskListener {
    private val viewModel : TaskViewModel by viewModels()
    private val taskAdapter = TaskAdapter(this)

    private lateinit var binding : FragmentTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaskBinding.bind(view)

        binding.apply {
            recyclerView.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        setObserve()
    }

    override fun updateTask(task: TaskEntity) {
        viewModel.updateTask(task)
    }

    private fun setObserve(){
        viewModel.tasksFavouriteLiveData.observe(viewLifecycleOwner) { tasks ->
            tasks?.let{
                updateUi(tasks)
            }
        }
    }

    private fun updateUi(tasks: List<TaskEntity>){
        taskAdapter.setTasks(tasks)
        binding.recyclerView.adapter = taskAdapter
    }

}