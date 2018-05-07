package com.yoesuv.infomadiun.menu.other.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.utils.AppHelper
import kotlinx.android.synthetic.main.child_fragment_changelog.view.*

class ChildFragmentChangelog: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.child_fragment_changelog, container, false)

        view.textViewChangelogOneDesc.text = AppHelper.fromHtml(getString(R.string.ver_1_info))

        return view
    }

}