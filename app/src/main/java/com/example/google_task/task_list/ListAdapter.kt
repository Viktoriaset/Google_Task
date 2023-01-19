package com.example.google_task.task_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.task.TaskFragment

class ListAdapter(private val fragment: ListFragment): FragmentStateAdapter(fragment) {

    private var  lists : List<ListEntity> = ArrayList<ListEntity>()
    override fun getItemCount(): Int {
        return lists.size + 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment
        if (position == 0 || position == lists.size + 1){
            fragment = TaskFragment(true)
        } else  {
            fragment = TaskFragment(false)
            fragment.arguments = Bundle().apply {
                putString(TaskFragment.LIST_ID, lists[position - 1].listId.toString())
            }
        }
        return fragment
    }

    fun setLists(lists: List<ListEntity>){
        this.lists = lists
        notifyDataSetChanged()
    }

    fun getListUUID(position: Int): String{
        return lists[position-1].listId.toString()
    }

    fun getList(position: Int): ListEntity{
        return lists[position-1]
    }

}