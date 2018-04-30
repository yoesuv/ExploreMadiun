package com.yoesuv.infomadiun.menu.listplace.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R

/**
 *  Created by yusuf on 4/30/18.
 */
class FragmentListPlace: Fragment() {

    companion object {
        fun getInstance():Fragment{
            return FragmentListPlace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_listplace, container, false)
        return v
    }

}