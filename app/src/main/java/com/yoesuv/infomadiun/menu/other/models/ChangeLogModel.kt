package com.yoesuv.infomadiun.menu.other.models

import com.google.errorprone.annotations.Keep

@Keep
data class ChangeLogModel(
    val title: String?,
    val description: String?,
    val isLast: Boolean?
)