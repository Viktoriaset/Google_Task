package com.example.google_task.task_list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.example.google_task.R
import com.example.google_task.databinding.ActivityMainBinding

class ListCreatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var listNameText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_creator)

        listNameText = findViewById(R.id.editTextListName)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_list_creator, menu)
        return super.onCreateOptionsMenu(menu)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.list_create_menu_button){
            val result = Intent().putExtra(ListCreatorContract.LIST_NAME, listNameText.text.toString())
            setResult(Activity.RESULT_OK, result)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}