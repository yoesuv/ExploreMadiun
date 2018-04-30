package com.yoesuv.infomadiun.menu.gallery.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentGallery: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return FragmentGallery()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gallery, container, false)

        val activity:AppCompatActivity = activity as AppCompatActivity
        val toolbar = activity.findViewById<Toolbar>(R.id.toolbarMain)
        toolbar.textViewToolbar.text = getString(R.string.menu_gallery)

        return v
    }

}