package com.example.checkip.feature.add.presentation

import android.util.Patterns
import com.example.checkip.domain.IPPoint
import com.example.checkip.data.IPListDao
import com.example.checkip.domain.IPSpamCheckUseCase
import com.example.checkip.extensions.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

class AddIPPresenter(
    private val IPListDao: IPListDao,
    private val ipSpamCheckUseCase: IPSpamCheckUseCase
) : MvpPresenter<AddIPView>() {

    fun addIP(ip: String) {
        if (this.checkIP(ip)) {
            if (!IPListDao.isInList(IPPoint(ip = ip))) {
                viewState.showLoading(isShow = true)
                presenterScope.launchWithErrorHandler(block = {
                    val ip = ipSpamCheckUseCase(ip);
                    IPListDao.add(ip)
                    viewState.openIPList()
                    viewState.showLoading(isShow = false)
                }, onError = {
                    viewState.errorAPI()
                    viewState.showLoading(isShow = false)
                })
            }
            else {
                viewState.existIP()
            }
        }
        else {
            viewState.invalidIP()
        }

    }

    private fun checkIP(ip: String): Boolean = Patterns.IP_ADDRESS.matcher(ip).matches();

}

interface AddIPView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openIPList()

    @StateStrategyType(SkipStrategy::class)
    fun invalidIP()

    @StateStrategyType(SkipStrategy::class)
    fun existIP()

    @StateStrategyType(SkipStrategy::class)
    fun errorAPI()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(isShow: Boolean)

}