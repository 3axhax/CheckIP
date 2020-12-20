package com.example.checkip.feature.list.presentation

import com.example.checkip.IPPoint
import com.example.checkip.data.IPListDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

class ListIPPresenter(
    private val IPListDao: IPListDao
) : MvpPresenter<ListIPView>() {

    private var ips = emptyList<IPPoint>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        ips = IPListDao.getAll()
        viewState.showIPs(ips)
    }

    fun onIPClick(ip: IPPoint) {
        viewState.openDetailScreen(ip)
    }

    fun onIPDelete(ip: IPPoint) {
        IPListDao.delete(ip)
        ips = IPListDao.getAll()
        viewState.showIPs(ips)
    }

    fun onFilterClick() {
        viewState.openFilterScreen()
    }

    fun onAddClick() {
        viewState.openAddScreen()
    }
}

interface ListIPView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showIPs(ips: List<IPPoint>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetailScreen(ip: IPPoint)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFilterScreen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openAddScreen()

}