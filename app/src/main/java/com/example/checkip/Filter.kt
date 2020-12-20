package com.example.checkip

import kotlinx.serialization.Serializable

@Serializable
data class Filter(val search: String = "")