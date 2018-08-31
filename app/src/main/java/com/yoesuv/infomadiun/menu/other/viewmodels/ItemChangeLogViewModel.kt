package com.yoesuv.infomadiun.menu.other.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.utils.AppHelper

class ItemChangeLogViewModel(changeLogModel: ChangeLogModel): ViewModel() {

    var name: ObservableField<String> = ObservableField(changeLogModel.title!!)
    var description: ObservableField<String> = ObservableField(AppHelper.fromHtml(changeLogModel.description!!))
    var isLast: ObservableField<Boolean> = ObservableField(changeLogModel.isLast!!)

}