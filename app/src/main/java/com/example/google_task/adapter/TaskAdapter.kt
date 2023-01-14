package com.example.google_task.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_task.R
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.TaskSchemeBinding


class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    var taskList = ArrayList<TaskEntity>()

    class TaskHolder (item : View) : RecyclerView.ViewHolder(item){
        val binding = TaskSchemeBinding.bind(item)

        fun bind(task: TaskEntity) = with(binding){
            taskText.text = task.taskText
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(task: TaskEntity){
        taskList.add(task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_scheme, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}