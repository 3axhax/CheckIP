package com.example.checkip

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val test1 = IPPoint("1.2.3.4", "w-host.org")
    private val test2 = IPPoint("77.6.5.4", "def.com")
    private val test3 = IPPoint("44.44.55.55", "abc.com")

    private var collection = mutableListOf(test1, test2, test3)

    @Test
    fun start() {
        println(collection)
        collection.sortPoint("hostname")
        println(collection)
        collection.sortPoint("ip")
        println(collection)
    }

    private fun MutableList<IPPoint>.sortPoint(field: String = "ip"): MutableList<IPPoint> {
        val test = this
        if (field in listOf("ip", "hostname")) {
            when(field) {
                "hostname" -> this.sortBy{it.hostname}
                "ip" -> this.sortWith (fun (a: IPPoint, b: IPPoint) : Int{
                    val ipA = a.ip.split('.')
                    val ipB = b.ip.split('.')
                    if (ipA.size != 4 || ipB.size != 4)  return 0
                    for (i in ipA.indices) {
                        if (ipA[i].toInt() > ipB[i].toInt()) return 1
                        if (ipA[i].toInt() < ipB[i].toInt()) return -1
                    }
                    return 0
                })
            }
        }
        return this
    }
}