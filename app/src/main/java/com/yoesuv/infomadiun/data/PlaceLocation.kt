package com.yoesuv.infomadiun.data

enum class PlaceLocation {
    ALL,
    KAB_MADIUN {
        override fun toString(): String {
            return "Kab. Madiun"
        }
    },
    KAB_MAGETAN {
        override fun toString(): String {
            return "Kab. Magetan"
        }
    },
    KAB_NGAWI {
        override fun toString(): String {
            return "Kab. Ngawi"
        }
    },
    KAB_PACITAN {
        override fun toString(): String {
            return "Kab. Pacitan"
        }
    },
    KAB_PONOROGO {
        override fun toString(): String {
            return "Kab. Ponorogo"
        }
    },
    KOTA_MADIUN {
        override fun toString(): String {
            return "Kota Madiun"
        }
    }
}