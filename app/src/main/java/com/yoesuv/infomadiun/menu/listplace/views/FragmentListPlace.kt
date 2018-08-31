package com.yoesuv.infomadiun.menu.listplace.views

import android.app.Activity
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.*
import android.view.*
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentListplaceBinding
import com.yoesuv.infomadiun.menu.listplace.adapters.ListPlaceAdapter
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.viewmodels.FragmentListPlaceViewModel

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentListPlace: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return FragmentListPlace()
        }
    }

    private lateinit var binding: FragmentListplaceBinding
    private lateinit var viewModel: FragmentListPlaceViewModel

    private var listPlace: MutableList<PlaceModel> = mutableListOf()
    private lateinit var adapter:ListPlaceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listplace, container, false)
        viewModel = FragmentListPlaceViewModel()
        binding.listPlace = viewModel

        setupRecycler()
        setupSwipeRefresh()

        setHasOptionsMenu(true)

        viewModel.getListPlace()
        viewModel.listPlace.observe(this, Observer {
            onListDataChanged(it!!)
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_list_place, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.listSemua -> viewModel.getListPlace()
            R.id.listKabMadiun -> viewModel.getListPlaceKabMadiun()
            R.id.listKabMagetan -> viewModel.getListPlaceKabMagetan()
            R.id.listKabNgawi -> viewModel.getListPlaceKabNgawi()
            R.id.listKabPacitan -> viewModel.getListPlaceKabPacitan()
            R.id.listKabPonorogo -> viewModel.getListPlaceKabPonorogo()
            R.id.listKotaMadiun-> viewModel.getListPlaceKotaMadiun()
        }
        item?.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecycler(){
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerViewListPlace.layoutManager = layoutManager
        binding.recyclerViewListPlace.setHasFixedSize(true)

        adapter = ListPlaceAdapter(activity as Activity, listPlace, binding.recyclerViewListPlace)
        binding.recyclerViewListPlace.adapter = adapter
        binding.recyclerViewListPlace.itemAnimator = DefaultItemAnimator()
    }

    private fun setupSwipeRefresh(){
        binding.swipeRefreshListPlace.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary))
        binding.swipeRefreshListPlace.setOnRefreshListener{
            viewModel.getListPlace()
            binding.swipeRefreshListPlace.isRefreshing = false
            activity?.invalidateOptionsMenu()
        }
    }

    private fun onListDataChanged(listData: MutableList<PlaceModel>){
        listPlace.clear()
        listPlace.addAll(listData)
        binding.recyclerViewListPlace.post {
            adapter.notifyDataSetChanged()
        }
        binding.recyclerViewListPlace.scrollToPosition(0)
    }

}