package com.yoesuv.infomadiun.menu.other.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.child_fragment_thanks.view.*

class ChildFragmentThanks: Fragment(){

    companion object {
        fun getInstance(): Fragment{
            return ChildFragmentThanks()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.child_fragment_thanks, container, false)
        view.textViewThanks.text = AppHelper.fromHtml(getString(R.string.trims))
        return view
    }

}