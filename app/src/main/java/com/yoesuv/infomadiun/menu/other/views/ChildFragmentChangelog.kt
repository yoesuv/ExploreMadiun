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
        view.textViewChangelogTwoDesc.text = AppHelper.fromHtml(getString(R.string.ver_2_info))
        view.textViewChangelogThreeDesc.text = AppHelper.fromHtml(getString(R.string.ver_3_info))
        view.textViewChangelogFourDesc.text = AppHelper.fromHtml(getString(R.string.ver_4_info))
        view.textViewChangelogFiveDesc.text = AppHelper.fromHtml(getString(R.string.ver_5_info))
        view.textViewChangelogSixDesc.text = AppHelper.fromHtml(getString(R.string.ver_6_info))
        view.textViewChangelogSevenDesc.text = AppHelper.fromHtml(getString(R.string.ver_7_info))
        view.textViewChangelogEightDesc.text = AppHelper.fromHtml(getString(R.string.ver_8_info))

        return view
    }

}