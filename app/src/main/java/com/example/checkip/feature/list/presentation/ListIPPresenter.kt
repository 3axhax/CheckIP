package com.example.checkip.feature.list.presentation

import com.example.checkip.IPPoint
import com.example.checkip.data.FilterDao
import com.example.checkip.data.IPListDao
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

class ListIPPresenter(
    private val IPListDao: IPListDao,
    private val FilterDao: FilterDao
) : MvpPresenter<ListIPView>() {

    private var ips = emptyList<IPPoint>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        ips = IPListDao.getAll()
        viewState.showIPs(applyFilter(ips))
    }

    fun onIPClick(ip: IPPoint) {
        viewState.openDetailScreen(ip)
    }

    fun onIPDelete(ip: IPPoint) {
        IPListDao.delete(ip)
        ips = IPListDao.getAll()
        viewState.showIPs(applyFilter(ips))
    }

    fun onFilterClick() {
        viewState.openFilterScreen()
    }

    fun onAddClick() {
        viewState.openAddScreen()
    }

    fun resetFilter() {
        FilterDao.resetFilter()
        viewState.showIPs(applyFilter(ips))
    }

    private fun applyFilter(ips: List<IPPoint>) : List<IPPoint> {
        var filtered = ips
        if (FilterDao.isSetSearchField()) {
            filtered = filtered.filter {
                it.ip.indexOf(FilterDao.getSearchString()) >= 0
            }
        }
        viewState.showClearFilter(filtered != ips)
        return filtered
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

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showClearFilter(show: Boolean)

}