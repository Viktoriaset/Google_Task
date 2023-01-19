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
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.databinding.FragmentListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ListAdapter

    private var activityLauncher = registerForActivityResult(ListCreatorContract()){
        if (it.size == 1){
            val list = ListEntity(listName = it[0].toString())
            viewModel.insertList(list)
        } else if (it.size == 2){
            val list = ListEntity(listId = UUID.fromString(it[1]), listName = it[0])
            viewModel.updateList(list)
        } else {
            binding.tabLayoutList.selectTab(binding.tabLayoutList.getTabAt(0))
        }
    }

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

        binding.buttomRedactorList.setOnClickListener{
            showListRedactor()
        }

        viewModel.listsLiveData.observe(viewLifecycleOwner){
            it?.let{
                configurationTabLayout(it)
            }
        }
    }

    private fun configurationTabLayout(it: List<ListEntity>){
        listAdapter.setLists(it)
        binding.viewPager.adapter = listAdapter
        TabLayoutMediator(binding.tabLayoutList, binding.viewPager) {tab, position ->
            if (position == 0){
                tab.icon = AppCompatResources.getDrawable(
                    binding.tabLayoutList.context,
                    R.drawable.ic_baseline_star_24)
            } else if (position - 1 < it.size){
                tab.text = it[position - 1].listName
                tab.view.setOnClickListener(null)
            } else if (position == it.size + 1){
                tab.text = "Create List"
                tab.view.setOnClickListener {
                    activityLauncher.launch(ListCreatorContract.CREATE_LIST)
                }
            }
        }.attach()
    }

    private fun showTaskCreator(){

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.task_creator_bottom_sheet_dialog)

        val textTaskName: EditText? = bottomSheetDialog.findViewById(R.id.editTextTaskName)
        val textDescription: EditText? = bottomSheetDialog.findViewById(R.id.editTextTaskDisription)
        val checkBoxIsFavourite: CheckBox? = bottomSheetDialog.findViewById(R.id.checkBoxIsFavourite)
        val buttonAddDescription: Button? = bottomSheetDialog.findViewById(R.id.addDescriptionButton)
        val buttonSave: Button? = bottomSheetDialog.findViewById(R.id.saveButton)


        textTaskName?.let {
            it.doOnTextChanged{ text, start, before, count ->
                if (text.toString().isEmpty()) {
                    buttonSave?.isClickable = false
                    buttonSave?.setTextColor(resources.getColor(R.color.gray))
                } else {
                    buttonSave?.isClickable = true
                    buttonSave?.setTextColor(resources.getColor(R.color.black))
                }
            }
        }

        buttonAddDescription?.let { it ->
            it.setOnClickListener {
                textDescription?.visibility = View.VISIBLE
            }
        }

        buttonSave?.let {
            it.setOnClickListener{
                var tabPosition = binding.tabLayoutList.selectedTabPosition
                var listIdString = listAdapter.getListUUID(tabPosition)
                var listUUID = UUID.fromString(listIdString)
                val taskName = textTaskName?.text ?: ""
                val taskDescription = textDescription?.text ?: ""
                val isFavourite = checkBoxIsFavourite?.isChecked ?: false
                val newTask = TaskEntity(
                    listId = listUUID,
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

    private fun showListRedactor(){
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.list_redactor_bottom_sheet)

        val buttonRedactorListName : Button? = bottomSheetDialog.findViewById(R.id.buttonRenameList)
        val buttonDeleteList: Button? = bottomSheetDialog.findViewById(R.id.buttonDeleteList)

        var tabPosition = binding.tabLayoutList.selectedTabPosition
        if (tabPosition == 0 || tabPosition == 1 || tabPosition == listAdapter.itemCount - 1){
            return
        }
        var listIdString = listAdapter.getListUUID(tabPosition)

        buttonRedactorListName?.let{
            it.setOnClickListener {
                activityLauncher.launch(listIdString)

            }
        }

        buttonDeleteList?.let {
            it.setOnClickListener {
                viewModel.deleteList(listAdapter.getList(tabPosition))
                bottomSheetDialog.hide()
            }
        }

        bottomSheetDialog.show()
    }


}