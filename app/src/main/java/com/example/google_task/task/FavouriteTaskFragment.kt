package com.example.google_task.task

import android.os.Bundle
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
class FavouriteTaskFragment : Fragment(R.layout.fragment_task) {
    private val viewModel : TaskViewModel by viewModels()
    private val taskListener = TaskListener( this)
    private val taskAdapter = TaskAdapter(taskListener)
    private lateinit var binding : FragmentTaskBinding

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

    private fun setObserve(){
        viewModel.tasksFavouriteLiveData.observe(viewLifecycleOwner) { tasks ->
            tasks?.let{
                taskAdapter.setTasks(tasks)
                binding.recyclerView.adapter = taskAdapter
            }
        }
    }

}