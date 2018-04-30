package com.yoesuv.infomadiun.menu.listplace.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.listplace.contracts.ListPlaceContract
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.presenters.ListPlacePresenter
import kotlinx.android.synthetic.main.activity_main.view.*

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_listplace, container, false)

        val activity:AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.list_place)

        listPlacePresenter = ListPlacePresenter(this)
        listPlacePresenter.getListPlace()

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        listPlacePresenter.destroy()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun setData(listPlaceModel: MutableList<PlaceModel>) {
        Log.d(Constants.TAG_DEBUG,"FragmentListPlace # jumlah lokasi ${listPlaceModel.size}")
    }

}