package com.yoesuv.infomadiun.menu.listplace.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentListplaceBinding
import com.yoesuv.infomadiun.menu.listplace.adapters.ListPlaceAdapter
import com.yoesuv.infomadiun.menu.listplace.viewmodels.FragmentListPlaceViewModel

/**
 *  Updated by yusuf on 29 July 2020.
 */
class FragmentListPlace: Fragment() {

    private lateinit var binding: FragmentListplaceBinding
    private val viewModel: FragmentListPlaceViewModel by activityViewModels()

    private lateinit var adapter:ListPlaceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listplace, container, false)
        binding.lifecycleOwner = this
        binding.listPlace = viewModel

        setHasOptionsMenu(true)
        setupRecycler()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListPlace()
        viewModel.listPlace.observe(viewLifecycleOwner, Observer { listPlace ->
            adapter.submitList(listPlace)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list_place, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.listSemua -> viewModel.getListPlace()
            R.id.listKabMadiun -> viewModel.getListPlaceKabMadiun()
            R.id.listKabMagetan -> viewModel.getListPlaceKabMagetan()
            R.id.listKabNgawi -> viewModel.getListPlaceKabNgawi()
            R.id.listKabPacitan -> viewModel.getListPlaceKabPacitan()
            R.id.listKabPonorogo -> viewModel.getListPlaceKabPonorogo()
            R.id.listKotaMadiun-> viewModel.getListPlaceKotaMadiun()
        }
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecycler(){
        adapter = ListPlaceAdapter().also {
            binding.recyclerViewListPlace.adapter = it
        }
    }

}