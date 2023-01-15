package com.example.google_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.google_task.databinding.ActivityMainBinding
import com.example.google_task.data.entities.ListEntity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var activityLauncher : ActivityResultLauncher<Intent>? = null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                var list = it.data?.getSerializableExtra("list") as ListEntity
            }
        }
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

    }


}