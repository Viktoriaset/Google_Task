package com.example.google_task.task_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.task.FavouriteTaskFragment
import com.example.google_task.task.TaskFragment

class ListAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private var  lists : List<ListEntity> = ArrayList<ListEntity>()
    override fun getItemCount(): Int {
        return lists.size + 1
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> {
                fragment = FavouriteTaskFragment()
            }
            else -> {
                fragment = TaskFragment()
                fragment.arguments = Bundle().apply {
                    putInt(TaskFragment.LIST_ID, lists[position - 1].listId)
                }
            }
        }
        return fragment
    }

    fun setLists(lists: List<ListEntity>){
        this.lists = lists
        notifyDataSetChanged()
    }
}