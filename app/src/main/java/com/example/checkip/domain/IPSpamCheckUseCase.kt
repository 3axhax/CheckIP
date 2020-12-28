package com.example.checkip.domain

import com.example.checkip.di.CleantalkSpamCheckAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class IPSpamCheckUseCase(
    private val cleantalkSpamCheckAPI: CleantalkSpamCheckAPI
) {
    suspend operator fun invoke(ipForCheck: String): IPPoint = withContext(Dispatchers.IO) {
        cleantalkSpamCheckAPI.getSpamCheckIP(ip = ipForCheck).run {
            if (this.data[ipForCheck] != null) {
                val ipInfo = this.data[ipForCheck]

                IPPoint(
                    ip = ipForCheck,
                    network_type = ipInfo?.networkType ?: "",
                    in_list = (ipInfo?.inAntispam ?: 0 > 0 || ipInfo?.inSecurity ?: 0 > 0),
                    country = ipInfo?.country ?: "",
                    frequency = ipInfo?.frequency ?: 0,
                    fullData = Json.encodeToString(ipInfo),
                )
            }
            else IPPoint()

        }
    }
}