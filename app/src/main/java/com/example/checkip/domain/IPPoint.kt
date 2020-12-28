package com.example.checkip.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class IPPoint(
    val ip: String = "0.0.0.0",
    val network_type: String = "type",
    val in_list: Boolean = false,
    val country: String = "US",
    val fullData: String = "",
    val frequency: Int = 0,
) : Parcelable