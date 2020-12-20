package com.example.checkip

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class IPPoint(
    val ip: String = "0.0.0.0",
    val network_type: String = "type",
    val in_list: Boolean = false,
    val last_activity: String = "2020-01-01 00:00:00",
    val country: String = "US",
) : Parcelable