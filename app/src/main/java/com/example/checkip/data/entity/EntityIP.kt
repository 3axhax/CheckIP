package com.example.checkip.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntityIP(
    @SerialName("appears")
    val appears: Int?,
    @SerialName("country")
    val country: String?,
    @SerialName("domains_count")
    val domainsCount: Int?,
    @SerialName("frequency")
    val frequency: Int?,
    @SerialName("frequency_time_10m")
    val frequencyTime10m: Int?,
    @SerialName("frequency_time_1h")
    val frequencyTime1h: Int?,
    @SerialName("frequency_time_24h")
    val frequencyTime24h: Int?,
    @SerialName("in_antispam")
    val inAntispam: Int?,
    @SerialName("in_security")
    val inSecurity: Int?,
    @SerialName("network_type")
    val networkType: String?,
)