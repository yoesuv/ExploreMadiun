package com.yoesuv.infomadiun.menu.listplace.views

import android.animation.Animator
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.data.Constants
import com.yoesuv.infomadiun.menu.listplace.adapters.ListPlaceAdapter
import com.yoesuv.infomadiun.menu.listplace.contracts.ListPlaceContract
import com.yoesuv.infomadiun.menu.listplace.models.PlaceModel
import com.yoesuv.infomadiun.menu.listplace.presenters.ListPlacePresenter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_listplace.*

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
    private var recyclerView:RecyclerView? = null
    private lateinit var progressBar:ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_listplace, container, false)

        val activity: AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.list_place)

        setupRecycler(v)
        setupSwipeRefresh(v)

        listPlacePresenter = ListPlacePresenter(this)
        listPlacePresenter.getListPlace()

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        listPlacePresenter.destroy()
    }

    private fun setupRecycler(view: View){
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView = view.findViewById(R.id.recyclerViewListPlace)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)

        adapter = ListPlaceAdapter(activity as Activity, listPlace)
        recyclerView?.adapter = adapter
    }

    private fun setupSwipeRefresh(view: View){
        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshListPlace)
        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(view.context, R.color.colorPrimary))
        swipeRefresh.setOnRefreshListener({
            listPlacePresenter.getListPlace()
            swipeRefresh.isRefreshing = false
        })
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        progressBar.alpha = 1f
        recyclerView?.alpha = 0f
    }

    override fun dismissLoading() {
        recyclerView?.animate()?.alpha(1f)?.duration = Constants.ANIMATION_TIME
        progressBar.animate().alpha(0f).setDuration(Constants.ANIMATION_TIME).setListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                progressBar.visibility = View.INVISIBLE
            }
        })
    }

    override fun setData(listPlaceModel: MutableList<PlaceModel>) {
        Log.d(Constants.TAG_DEBUG,"FragmentListPlace # jumlah lokasi ${listPlaceModel.size}")
        listPlace.clear()
        if(listPlaceModel.isNotEmpty()) {
            listPlace.addAll(listPlaceModel)
            recyclerView?.post({
                adapter.notifyDataSetChanged()
            })
        }
    }

}