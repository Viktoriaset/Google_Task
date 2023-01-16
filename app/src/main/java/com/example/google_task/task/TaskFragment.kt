package com.example.google_task.task


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.google_task.R
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.FragmentTaskBinding
import com.example.google_task.task_list.ListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskFragment() : Fragment(R.layout.fragment_task) {
    private val viewModel : TaskViewModel by viewModels()
    private val taskAdapter = TaskAdapter()

    private var listId: Int = 0
    private lateinit var binding : FragmentTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.takeIf { it.containsKey(LIST_ID)}?.apply {
            listId = getInt(LIST_ID)
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
                setHasFixedSize(true)
            }
        }

        viewModel.tasksLiveData.observe(viewLifecycleOwner) { tasks ->
            tasks?.let{
                updateUi(tasks)
            }
        }
    }

    private fun updateUi(tasks: List<TaskEntity>){
        taskAdapter.submitList(tasks)
    }

    companion object{
        const val LIST_ID = "LIST_ID"
    }

}