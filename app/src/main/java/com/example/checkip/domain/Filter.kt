package com.example.checkip.domain

import kotlinx.serialization.Serializable

@Serializable
data class Filter(val search: String = "")