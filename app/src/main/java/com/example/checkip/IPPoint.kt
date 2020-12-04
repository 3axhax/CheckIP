package com.example.checkip

data class IPPoint (var ip: String, val hostname: String) {

    private val defaultIp = "0.0.0.0"

    init {
        checkIP();
    }

    private fun checkIP() {
        val tmp = ip.split('.')
        if (tmp.size != 4) {
            ip = defaultIp
        }
        else {
            for (i in tmp.indices) {
                if (tmp[i].toInt() !in 0..255) {
                    ip = defaultIp
                    break
                }
            }
        }
    }
}