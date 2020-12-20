package com.example.checkip.data

import com.example.checkip.IPPoint

interface IPListDao {

    /**
     * Add [ip] to IPs List
     * */
    fun add(ip: IPPoint)

    /**
     * Delete IP from List
     * */
    fun delete(ip: IPPoint)

    /**
     * @return List of IPs
     * */
    fun getAll(): List<IPPoint>

    /**
     * @return Is IP in List
     */
    fun isInList(ip: IPPoint) : Boolean

}