package com.yoesuv.infomadiun.menu.other.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yoesuv.infomadiun.menu.other.models.ChangeLogModel
import com.yoesuv.infomadiun.utils.AppHelper

class ItemChangeLogViewModel(
    changeLogModel: ChangeLogModel,
) : ViewModel() {
    var name: ObservableField<String> = ObservableField(changeLogModel.title!!)
    var description: ObservableField<String> = ObservableField(AppHelper.fromHtml(changeLogModel.description!!))
    var isLast: ObservableField<Boolean> = ObservableField(changeLogModel.isLast!!)
}
