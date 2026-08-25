package com.yoesuv.infomadiun.menu.other.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yoesuv.infomadiun.menu.other.models.LicenseModel
import com.yoesuv.infomadiun.utils.AppHelper

class ItemLibrariesViewModel(
    licenseModel: LicenseModel,
) : ViewModel() {
    var name: ObservableField<String> = ObservableField(licenseModel.title!!)
    var url: ObservableField<String> = ObservableField(licenseModel.url!!)
    var license: ObservableField<String> = ObservableField(AppHelper.fromHtml(licenseModel.license!!))
    var isLast: ObservableField<Boolean> = ObservableField(licenseModel.isLast!!)
}
