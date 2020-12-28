package com.example.checkip.data

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.example.checkip.domain.IPPoint

class IPListDaoImpl(private val sharedPreferences: SharedPreferences) : IPListDao {

    private var ips: List<IPPoint>
        get() = sharedPreferences.getString(IPSLIST_DAO_KEY, null)?.let {
            try {
                Json.decodeFromString(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()
        set(value) {
            sharedPreferences.edit().putString(
                IPSLIST_DAO_KEY,
                Json.encodeToString(value)
            ).apply()
        }

    override fun add(ip: IPPoint) {
        if (!this.isInList(ip)) {
            ips = ips + ip
        }
    }

    override fun delete(ip: IPPoint) {
        ips = ips.filter { it.ip != ip.ip }
    }

    override fun getAll(): List<IPPoint> = ips

    override fun isInList(ip: IPPoint): Boolean {
        ips.forEach { if(it.ip == ip.ip) return true }
        return false
    }

    override fun replace(ip: IPPoint) {
        if(this.isInList(ip)) {
            var tmpIps: List<IPPoint> = emptyList()
            ips.forEach {
                if (it.ip == ip.ip) {
                    tmpIps = tmpIps + ip
                }
                else tmpIps = tmpIps + it
            }
            ips = tmpIps
            /*this.delete(ip)
            this.add(ip)*/
        }
    }

    companion object {
        private const val IPSLIST_DAO_KEY = "IPSLIST_DAO_KEY"
    }
}