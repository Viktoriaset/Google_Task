package com.example.google_task.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.ItemTaskBinding
import javax.inject.Inject

class TaskAdapter(
    private val taskListener: TaskListener
) : RecyclerView.Adapter<TaskAdapter.TasksViewHolder>() {

    private var tasks: List<TaskEntity> = ArrayList<TaskEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding, taskListener)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = tasks[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(tasks: List<TaskEntity>){
        this.tasks = tasks
    }


    class TasksViewHolder(
        private val binding: ItemTaskBinding,
        private val taskListener: TaskListener
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskEntity) {
            binding.apply {
                checkBoxCompleted.isChecked = task.isCompleted
                checkBoxFavorite.isChecked = task.isFavorite
                textViewName.text = task.taskText
                textViewName.paint.isStrikeThruText = task.isCompleted

                checkBoxFavorite.setOnClickListener{
                    task.isFavorite = !task.isFavorite
                    taskListener.updateTask(task)
                }

                checkBoxCompleted.setOnClickListener{
                    task.isCompleted = !task.isCompleted
                    taskListener.updateTask(task)
                }

            }
        }
    }
}

