package com.example.google_task.task_list

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ListCreatorContract: ActivityResultContract<String, String>() {

    override fun createIntent(context: Context, input: String): Intent =
        Intent(context, ListCreatorActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        val listName = intent?.getStringExtra(LIST_NAME)
        return if (listName.isNullOrEmpty()){
            ""
        } else {
            listName
        }
    }

    companion object{
        const val LIST_NAME = "listName"
    }
}