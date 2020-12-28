package com.example.checkip.di

import com.example.checkip.data.entity.CTSpamCheckResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CleantalkSpamCheckAPI {

    @GET("/")
    suspend fun getSpamCheckIP(
        @Query("ip") ip: String,
        @Query("method_name") method_name: String = "spam_check",
        @Query("auth_key") auth_key: String = "cc1i43z6t36i"
    ): CTSpamCheckResponse
}