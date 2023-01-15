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
class TaskFragment : Fragment(R.layout.fragment_task) {
    private val viewModel : TaskViewModel by viewModels()
    private val taskAdapter = TaskAdapter()

    private lateinit var binding : FragmentTaskBinding

    /*private var mPage : Int = 0

    constructor(page: Int){
        var args : Bundle = Bundle()
        args.putInt(ARG_PAGE, page)
    }*/


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

        viewModel.insertTask(TaskEntity(listId = 0, taskText = "3 task"))

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
        const val ARG_PAGE : String = "ARG_PAGE"
    }

}