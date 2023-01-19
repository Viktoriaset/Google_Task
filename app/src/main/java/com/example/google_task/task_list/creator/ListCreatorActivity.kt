package com.example.google_task.task_list.creator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.google_task.R
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.databinding.ActivityListCreatorBinding
import com.example.google_task.task_list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCreatorActivity : AppCompatActivity() {

    private val viewModel : ListViewModel by viewModels()
    private lateinit var binding: ActivityListCreatorBinding
    private lateinit var list: ListEntity
    private var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_creator)

        binding = ActivityListCreatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listUUIDString : String? = intent.getStringExtra(ListCreatorContract.UPDATE_LIST)
        listUUIDString?.let {
            viewModel.getListByUUID(it).observe(this, Observer {
                list = it
                binding.editTextListName.text.insert(0, it.listName)
                isUpdate = true
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_list_creator, menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.list_create_menu_button){
            val result = Intent().putExtra(
                ListCreatorContract.LIST_NAME,
                    binding.editTextListName.text.toString()
                )
            if(isUpdate) {
                result.putExtra(
                    ListCreatorContract.UPDATE_LIST,
                        list.listId.toString()
                    )
            }
            setResult(Activity.RESULT_OK, result)
            finish()
        }
        if (item.itemId == android.R.id.home){
            val result = Intent()
            setResult(Activity.RESULT_CANCELED, result)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}