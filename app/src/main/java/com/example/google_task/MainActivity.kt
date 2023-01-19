package com.example.google_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextPaint
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.google_task.databinding.ActivityMainBinding
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.task_list.ListAdapter
import com.example.google_task.task_list.ListCreatorContract
import com.example.google_task.task_list.ListFragment
import com.example.google_task.task_list.ListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onCLick_createTask(view: View){
   }
    //Configuration bottom sheet dialog for create task


    fun onClick_createList(view: View){

    }

}