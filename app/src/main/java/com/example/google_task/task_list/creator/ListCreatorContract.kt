package com.example.google_task.task_list.creator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ListCreatorContract: ActivityResultContract<String, List<String>>() {

    override fun createIntent(context: Context, input: String): Intent {
        if (input == CREATE_LIST){
            return Intent(context, ListCreatorActivity::class.java)
        }
        else {
            val newIntent = Intent(context, ListCreatorActivity::class.java)
            newIntent.putExtra(UPDATE_LIST, input)
            return newIntent
        }
    }


    override fun parseResult(resultCode: Int, intent: Intent?): List<String> {
        var resultList = ArrayList<String>()
        if (resultCode == Activity.RESULT_OK) {
            val listName = intent?.getStringExtra(LIST_NAME)
            val listUUIDString = intent?.getStringExtra(UPDATE_LIST)
            listName?.let {
                resultList.add(listName)
            }
            listUUIDString?.let {
                resultList.add(listUUIDString)
            }
            return resultList
        }
        return resultList
    }

    companion object{
        const val LIST_NAME = "listName"
        const val CREATE_LIST = "createList"
        const val UPDATE_LIST = "updateList"
    }
}