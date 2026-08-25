package com.yoesuv.infomadiun.data

enum class PlaceLocation {
    ALL,
    KAB_MADIUN {
        override fun toString(): String = "Kab. Madiun"
    },
    KAB_MAGETAN {
        override fun toString(): String = "Kab. Magetan"
    },
    KAB_NGAWI {
        override fun toString(): String = "Kab. Ngawi"
    },
    KAB_PACITAN {
        override fun toString(): String = "Kab. Pacitan"
    },
    KAB_PONOROGO {
        override fun toString(): String = "Kab. Ponorogo"
    },
    KOTA_MADIUN {
        override fun toString(): String = "Kota Madiun"
    },
}
