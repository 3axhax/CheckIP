package com.example.checkip.feature.add.presentation

import com.example.checkip.IPPoint
import com.example.checkip.data.IPListDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

class AddIPPresenter(
    private val IPListDao: IPListDao
) : MvpPresenter<AddIPView>() {

    fun addIP(ip: String) {
        if (this.checkIP(ip)) {
            IPListDao.add(IPPoint(ip))
            viewState.openIPList()
        }
        else {
            viewState.showIPError()
        }

    }

    private fun checkIP(ip: String): Boolean {
        val tmp = ip.split('.')
        if (tmp.size != 4) {
            return false
        }
        else {
            for (i in tmp.indices) {
                if (tmp[i] != "") {
                    if (tmp[i].toInt() !in 0..255) {
                        return false
                    }
                }
                else return false
            }
        }
        return true
    }

}

interface AddIPView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openIPList()

    @StateStrategyType(SkipStrategy::class)
    fun showIPError()

}