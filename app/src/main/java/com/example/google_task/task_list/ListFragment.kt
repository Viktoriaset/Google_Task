package com.example.google_task.task_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.google_task.R
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.FragmentListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        listAdapter = ListAdapter(this)
        binding.viewPager.adapter = listAdapter

        binding.createTaskButton.setOnClickListener{
            showTaskCreator()
        }

        viewModel.listsLiveData.observe(viewLifecycleOwner){
            it?.let{
                listAdapter.setLists(it)
                binding.viewPager.adapter = listAdapter
                TabLayoutMediator(binding.tabLayoutList, binding.viewPager) {tab, position ->
                    if (position == 0){
                        tab.icon = AppCompatResources.getDrawable(
                            binding.tabLayoutList.context,
                            R.drawable.ic_baseline_star_24)
                    } else if (position - 1 < it.size){
                        tab.text = it[position - 1].listName
                    } else if (position == it.size + 1){
                        tab.text = "Create List"
                    }
                }.attach()
            }
        }
    }

    private fun showTaskCreator(){

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.task_creator_bottom_sheet_dialog)

        val taskNameText : EditText? = bottomSheetDialog.findViewById<EditText>(R.id.editTextTaskName)
        val descriptionText : EditText? = bottomSheetDialog.findViewById(R.id.editTextTaskDisription)
        val isFavouriteCheckBox: CheckBox? = bottomSheetDialog.findViewById(R.id.checkBoxIsFavourite)
        val addDescriptionButton: Button? = bottomSheetDialog.findViewById(R.id.addDescriptionButton)
        val saveButton: Button? = bottomSheetDialog.findViewById(R.id.saveButton)


        taskNameText?.let {
            it.doOnTextChanged{ text, start, before, count ->
                if (text.toString().isEmpty()) {
                    saveButton?.isClickable = false
                    saveButton?.setTextColor(resources.getColor(R.color.gray))
                } else {
                    saveButton?.isClickable = true
                    saveButton?.setTextColor(resources.getColor(R.color.black))
                }
            }
        }

        addDescriptionButton?.let { it ->
            it.setOnClickListener {
                descriptionText?.visibility = View.VISIBLE
            }
        }

        saveButton?.let {
            it.setOnClickListener{
                var listId = binding.tabLayoutList.selectedTabPosition
                if (listId == 0){
                    listId = 1
                }
                val taskName = taskNameText?.text ?: ""
                val taskDescription = descriptionText?.text ?: ""
                val isFavourite = isFavouriteCheckBox?.isChecked ?: false
                val newTask = TaskEntity(
                    listId = listId,
                    taskText = taskName.toString(),
                    taskDescription = taskDescription.toString(),
                    isFavorite = isFavourite)

                viewModel.insertTask(newTask)
                bottomSheetDialog.hide()
            }
            it.isClickable = false
        }
        bottomSheetDialog.show()
    }


}