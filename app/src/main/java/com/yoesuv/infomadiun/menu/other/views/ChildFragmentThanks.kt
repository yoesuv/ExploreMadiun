package com.yoesuv.infomadiun.menu.other.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.databinding.ChildFragmentThanksBinding
import com.yoesuv.infomadiun.utils.AppHelper

class ChildFragmentThanks : Fragment() {
    companion object {
        fun getInstance(): Fragment = ChildFragmentThanks()
    }

    private lateinit var binding: ChildFragmentThanksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.child_fragment_thanks, container, false)
        binding.textViewThanks.text = AppHelper.fromHtml(getString(R.string.trims))
        return binding.root
    }
}
