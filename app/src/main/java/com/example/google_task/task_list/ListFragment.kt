package com.example.google_task.task_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.google_task.R
import com.example.google_task.databinding.FragmentListBinding
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

        viewModel.listsLiveData.observe(viewLifecycleOwner){
            it?.let{
                listAdapter.setLists(it)
            }
        }
    }

}