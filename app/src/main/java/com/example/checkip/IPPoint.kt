package com.example.checkip

data class IPPoint (var ip: String, val hostname: String, var type: String) {

    private val defaultIp = "0.0.0.0"
    private val typeList = listOf("public", "hosting", "good_bot", "unknown")

    init {
        checkIP()
        checkType()
    }

    private fun checkType() {
        if (type !in typeList) type = "unknown"
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