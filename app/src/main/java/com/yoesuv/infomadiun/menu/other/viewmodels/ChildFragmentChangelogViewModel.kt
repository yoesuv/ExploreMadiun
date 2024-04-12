package com.yoesuv.infomadiun.menu.other.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.yoesuv.infomadiun.R
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel

class ChildFragmentChangelogViewModel : ViewModel() {

    private var listChangelog: MutableList<ChangeLogModel> = mutableListOf()
    var listData: MutableLiveData<MutableList<ChangeLogModel>> = MutableLiveData()

    fun setupData(context: Context?) {
        listChangelog.clear()
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_16), context?.getString(R.string.ver_16_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_15), context?.getString(R.string.ver_15_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_14), context?.getString(R.string.ver_14_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_13), context?.getString(R.string.ver_13_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_12), context?.getString(R.string.ver_12_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_11), context?.getString(R.string.ver_11_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_10), context?.getString(R.string.ver_10_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_9), context?.getString(R.string.ver_9_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_8), context?.getString(R.string.ver_8_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_7), context?.getString(R.string.ver_7_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_6), context?.getString(R.string.ver_6_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_5), context?.getString(R.string.ver_5_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_4), context?.getString(R.string.ver_4_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_3), context?.getString(R.string.ver_3_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_2), context?.getString(R.string.ver_2_info), false))
        listChangelog.add(ChangeLogModel(context?.getString(R.string.ver_1), context?.getString(R.string.ver_1_info), true))

        listData.value = listChangelog
    }

}