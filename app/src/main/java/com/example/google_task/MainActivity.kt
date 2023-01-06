package com.example.google_task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.example.google_task.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listsLayout : LinearLayout
    lateinit var activityTaskLayout: LinearLayout
    lateinit var activityTaskScrollView: ScrollView
    lateinit var emptyListText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listsLayout = binding.listsLayout
        activityTaskLayout = binding.activeTaskLayout
        activityTaskScrollView = binding.activeTaskScrollView
        emptyListText = binding.emptyListText
    }

    @SuppressLint("InflateParams")
    public fun onClick_create_list(view: View){
        val newButton = Button(this)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        newButton.layoutParams = layoutParams
        newButton.text = "custom name"

        listsLayout.addView(newButton)



    }

    public fun onCLick_create_task(view: View){
        var task: View = LayoutInflater.from(this).inflate(R.layout.task_scheme, activityTaskLayout, false)
        task.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        activityTaskLayout.addView(task)

        emptyListText.visibility = View.GONE
        activityTaskScrollView.visibility = View.VISIBLE
   }


}