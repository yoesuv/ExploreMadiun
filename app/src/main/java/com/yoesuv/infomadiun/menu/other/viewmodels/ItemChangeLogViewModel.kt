package com.yoesuv.infomadiun.menu.other.viewmodels

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.utils.AppHelper

class ItemChangeLogViewModel(changeLogModel: ChangeLogModel) : ViewModel() {

    var name: ObservableField<String> = ObservableField(changeLogModel.title!!)
    var description: ObservableField<String> = ObservableField(AppHelper.fromHtml(changeLogModel.description!!))
    var isLast: ObservableField<Boolean> = ObservableField(changeLogModel.isLast!!)

}