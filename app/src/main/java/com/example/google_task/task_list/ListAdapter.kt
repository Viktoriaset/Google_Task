package com.example.google_task.task_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.databinding.ActivityMainBinding
import com.example.google_task.task.TaskFragment

class ListAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private var  lists : List<ListEntity> = ArrayList<ListEntity>()
    override fun getItemCount(): Int {
        return lists.size
    }

    override fun createFragment(position: Int): Fragment {
        return TaskFragment(lists[position])
    }

    fun setLists(lists: List<ListEntity>){
        this.lists = lists
        notifyDataSetChanged()
    }
}