package com.example.google_task.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.ItemTaskBinding

class TaskAdapter : ListAdapter<TaskEntity, TaskAdapter.TasksViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    class TasksViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskEntity) {
            binding.apply {
                checkBoxCompleted.isChecked = task.isCompleted
                checkBoxFavorite.isChecked = task.isFavorite
                textViewName.text = task.taskText
                textViewName.paint.isStrikeThruText = task.isCompleted
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TaskEntity>(){
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem.taskId == newItem.taskId
        }

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem == newItem
        }

    }
}

