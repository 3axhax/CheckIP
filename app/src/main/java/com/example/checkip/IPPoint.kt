package com.example.checkip

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IPPoint(var ip: String, val hostname: String = "hostname", var type: String = "type") :
    Parcelable