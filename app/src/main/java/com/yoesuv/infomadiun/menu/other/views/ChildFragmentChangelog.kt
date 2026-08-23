package com.yoesuv.infomadiun.menu.other.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ChildFragmentChangelogBinding
import com.yoesuv.infomadiun.menu.other.adapters.ChangeLogAdapter
import com.yoesuv.infomadiun.menu.other.viewmodels.ChildFragmentChangelogViewModel

class ChildFragmentChangelog : Fragment() {
    companion object {
        fun getInstance(): Fragment = ChildFragmentChangelog()
    }

    private lateinit var binding: ChildFragmentChangelogBinding
    private lateinit var viewModel: ChildFragmentChangelogViewModel
    private lateinit var adapter: ChangeLogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_changelog, container, false)
        viewModel = ViewModelProvider(this)[ChildFragmentChangelogViewModel::class.java]
        binding.changelog = viewModel

        setupRecycler()
        viewModel.setupData(context)
        viewModel.listData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    private fun setupRecycler() {
        binding.recyclerViewChangelog.layoutManager = LinearLayoutManager(context)
        adapter = ChangeLogAdapter()
        binding.recyclerViewChangelog.adapter = adapter
    }
}
