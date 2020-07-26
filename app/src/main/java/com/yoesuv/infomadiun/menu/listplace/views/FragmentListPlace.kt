package com.yoesuv.infomadiun.menu.listplace.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.widget.*
import androidx.lifecycle.ViewModelProvider
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.FragmentListplaceBinding
import com.yoesuv.infomadiun.menu.listplace.adapters.ListPlaceAdapter
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.viewmodels.FragmentListPlaceViewModel

/**
 *  Updated by yusuf on 14 July 2020.
 */
class FragmentListPlace: Fragment() {

    private lateinit var binding: FragmentListplaceBinding
    private lateinit var viewModel: FragmentListPlaceViewModel

    private var listPlace: MutableList<PlaceModel> = mutableListOf()
    private lateinit var adapter:ListPlaceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listplace, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(FragmentListPlaceViewModel::class.java)
        binding.listPlace = viewModel

        setupRecycler()
        setupSwipeRefresh()
        setupLayoutError()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListPlace()
        viewModel.listPlace.observe(viewLifecycleOwner, Observer { listPlace ->
            onListDataChanged(listPlace!!)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_list_place, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
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
        adapter = ListPlaceAdapter()
        binding.recyclerViewListPlace.adapter = adapter
    }

    private fun setupSwipeRefresh(){
        binding.swipeRefreshListPlace.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
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

    private fun setupLayoutError(){
        binding.layoutError.findViewById<AppCompatButton>(R.id.buttonErrorRefresh).setOnClickListener {
            viewModel.getListPlace()
        }
    }

}