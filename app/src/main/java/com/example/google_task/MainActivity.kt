package com.example.google_task

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.google_task.databinding.ActivityMainBinding
import com.example.google_task.adapter.TaskAdapter
import com.example.google_task.data.dao.TaskDao
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.task.TaskDataSource
import com.example.google_task.task_list.TaskListDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
    taskDataSource: TaskDataSource,
    taskListDataSource: TaskListDataSource
) : AppCompatActivity() {

    private val adapter = TaskAdapter()
    private var activityLauncher : ActivityResultLauncher<Intent>? = null

    lateinit var binding: ActivityMainBinding
    lateinit var listsLayout : LinearLayout
    lateinit var emptyListText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listsLayout = binding.listsLayout
        emptyListText = binding.emptyListText


        activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                var list = it.data?.getSerializableExtra("list") as ListEntity
            }
        }

        init()
    }

    private fun init(){
        binding.apply {
            activeTaskRecycler.layoutManager = GridLayoutManager(this@MainActivity, 1)
            activeTaskRecycler.adapter = adapter
        }
    }

    @SuppressLint("InflateParams")
    public fun onClick_createList(view: View){
        val newButton = Button(this)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        newButton.layoutParams = layoutParams
        newButton.text = "custom name"

        listsLayout.addView(newButton)
    }

    public fun onCLick_create_task(view: View){

   }


}