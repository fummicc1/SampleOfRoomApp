package dev.fummicc1.sample.sampleofroomapp.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.fummicc1.sample.sampleofroomapp.R
import dev.fummicc1.sample.sampleofroomapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.apply {
            val adapter = TodoRecyclerViewAdapter(requireActivity())
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(requireActivity())

            floatingActionButton.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddTodoFragment())
            }
            viewModel.todos.observe(viewLifecycleOwner, {
                adapter.updateTodos(it)
            })
        }
        return binding.root
    }
}