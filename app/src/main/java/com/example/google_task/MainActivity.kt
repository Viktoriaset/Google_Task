package com.example.google_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.google_task.databinding.ActivityMainBinding
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