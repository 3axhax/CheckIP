package com.example.checkip.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CTSpamCheckResponse(
    @SerialName("data")
    val data: Map<String,EntityIP?>,
    @SerialName("error_message")
    val error_message: String? = "",
    @SerialName("error_no")
    val error_no: Int? = 0
)