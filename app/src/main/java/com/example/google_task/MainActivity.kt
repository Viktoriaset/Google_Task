package com.example.google_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.google_task.databinding.ActivityMainBinding
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.task_list.ListAdapter
import com.example.google_task.task_list.ListCreatorContract
import com.example.google_task.task_list.ListFragment
import com.example.google_task.task_list.ListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var activityLauncher = registerForActivityResult(ListCreatorContract()){

    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onCLick_createTask(view: View){
        showTaskCreator()
   }
    //Configuration bottom sheet dialog for create task
    private fun showTaskCreator(){
        val bottomSheetDialog : BottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.task_creator_bottom_sheet_dialog)
        bottomSheetDialog.show()
    }

    fun onClick_createList(view: View){
        activityLauncher.launch("create new list")
    }

}