package com.yoesuv.infomadiun.menu.other.views

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ChildFragmentThanksBinding
import com.yoesuv.infomadiun.utils.AppHelper

class ChildFragmentThanks : Fragment() {

    companion object {
        fun getInstance(): Fragment {
            return ChildFragmentThanks()
        }
    }

    private lateinit var binding: ChildFragmentThanksBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_thanks, container, false)
        binding.textViewThanks.text = AppHelper.fromHtml(getString(R.string.trims))
        return binding.root
    }

}