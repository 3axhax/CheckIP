package com.example.checkip.feature.list.presentation

import com.example.checkip.IPPoint
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

class ListIPPresenter : MvpPresenter<ListIPView>() {

    private var ips = listOf(
        IPPoint("1.1.1.1"),
        IPPoint("2.2.3.4"),
        IPPoint("2.5.7.9"),
        IPPoint("3.2.3.4"),
        IPPoint("250.26.33.40"),
        IPPoint("25.27.44.50"),
        IPPoint("77.56.89.99"),
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showIPs(ips)
    }

    fun onIPClick(ip: IPPoint) {
        viewState.openDetailScreen(ip)
    }

    fun onIPDelete(ip: IPPoint) {
        ips = ips.filter { it != ip }
        viewState.showIPs(ips)
    }
}

interface ListIPView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showIPs(ips: List<IPPoint>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetailScreen(ip: IPPoint)

}