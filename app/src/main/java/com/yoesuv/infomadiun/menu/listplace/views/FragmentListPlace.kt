package com.yoesuv.infomadiun.menu.listplace.views

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.Log
import android.view.*
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.listplace.adapters.ListPlaceAdapter
import com.yoesuv.infomadiun.menu.listplace.contracts.ListPlaceContract
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.presenters.ListPlacePresenter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_listplace.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentListPlace: Fragment(), ListPlaceContract.ViewListPlace {

    companion object {
        fun getInstance():Fragment{
            return FragmentListPlace()
        }
    }

    private lateinit var listPlacePresenter: ListPlacePresenter
    private var listPlace: MutableList<PlaceModel> = arrayListOf()
    private lateinit var adapter:ListPlaceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_listplace, container, false)

        val activity: AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.list_place)

        setupRecycler(v)
        setupSwipeRefresh(v)

        listPlacePresenter = ListPlacePresenter(this)
        listPlacePresenter.getListPlace()

        setHasOptionsMenu(true)
        v.layoutError.visibility = View.GONE

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        listPlacePresenter.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_list_place, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            R.id.listSemua -> listPlacePresenter.getListPlace()
            R.id.listKabMadiun -> listPlacePresenter.getListPlaceKabMadiun()
            R.id.listKabMagetan -> listPlacePresenter.getListPlaceKabMagetan()
            R.id.listKabNgawi -> listPlacePresenter.getListPlaceKabNgawi()
            R.id.listKabPacitan -> listPlacePresenter.getListPlaceKabPacitan()
            R.id.listKabPonorogo -> listPlacePresenter.getListPlaceKabPonorogo()
            R.id.listKotaMadiun-> listPlacePresenter.getListPlaceKotaMadiun()
        }
        item?.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecycler(view: View?){
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        view?.recyclerViewListPlace?.layoutManager = layoutManager
        view?.recyclerViewListPlace?.setHasFixedSize(true)

        adapter = ListPlaceAdapter(activity as Activity, listPlace, view?.recyclerViewListPlace)
        view?.recyclerViewListPlace?.adapter = adapter
        view?.recyclerViewListPlace?.itemAnimator = DefaultItemAnimator()
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            view?.recyclerViewListPlace?.isNestedScrollingEnabled = true
        }
    }

    private fun setupSwipeRefresh(view: View?){
        view?.swipeRefreshListPlace?.setColorSchemeColors(ContextCompat.getColor(view.context, R.color.colorPrimary))
        view?.swipeRefreshListPlace?.setOnRefreshListener({
            listPlacePresenter.getListPlace()
            view.swipeRefreshListPlace.isRefreshing = false
            activity?.invalidateOptionsMenu()
        })
    }

    override fun showLoading() {
        view?.progressBar?.visibility = View.VISIBLE
        view?.progressBar?.alpha = 1f
        view?.recyclerViewListPlace?.alpha = 0f
    }

    override fun dismissLoading() {
        view?.recyclerViewListPlace?.animate()?.alpha(1f)?.duration = Constants.ANIMATION_TIME
        view?.progressBar?.visibility = View.GONE
    }

    override fun setData(listPlaceModel: MutableList<PlaceModel>) {
        Log.d(Constants.TAG_DEBUG,"FragmentListPlace # jumlah lokasi ${listPlaceModel.size}")
        if(view?.swipeRefreshListPlace?.visibility==View.INVISIBLE){
            view?.swipeRefreshListPlace?.visibility = View.VISIBLE
        }
        listPlace.clear()
        if(listPlaceModel.isNotEmpty()) {
            listPlace.addAll(listPlaceModel)
            view?.recyclerViewListPlace?.post({
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun setError() {
        view?.layoutError?.visibility = View.VISIBLE
        view?.swipeRefreshListPlace?.visibility = View.INVISIBLE
        view?.layoutError?.findViewById<AppCompatButton>(R.id.buttonErrorRefresh)?.setOnClickListener({
            view?.layoutError?.visibility = View.INVISIBLE
            listPlacePresenter.getListPlace()
        })
    }

}