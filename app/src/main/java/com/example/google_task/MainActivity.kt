package com.example.google_task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.google_task.databinding.ActivityMainBinding
import com.example.google_task.data.entities.Task
import com.example.google_task.adapter.TaskAdapter


class MainActivity : AppCompatActivity() {

    private val adapter = TaskAdapter()

    lateinit var binding: ActivityMainBinding
    lateinit var listsLayout : LinearLayout
    lateinit var emptyListText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listsLayout = binding.listsLayout
        emptyListText = binding.emptyListText

        init()
    }

    @SuppressLint("InflateParams")
    public fun onClick_create_list(view: View){
        val newButton = Button(this)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        newButton.layoutParams = layoutParams
        newButton.text = "custom name"

        listsLayout.addView(newButton)
    }

    private fun init(){
        binding.apply {
            activeTaskRecycler.layoutManager = GridLayoutManager(this@MainActivity, 1)
            activeTaskRecycler.adapter = adapter
        }
    }

    public fun onCLick_create_task(view: View){
        val task = Task("temp task")
        adapter.addTask(task)
   }


}