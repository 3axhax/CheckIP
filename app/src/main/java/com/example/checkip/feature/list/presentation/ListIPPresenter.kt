package com.example.checkip.feature.list.presentation

import com.example.checkip.domain.IPPoint
import com.example.checkip.data.FilterDao
import com.example.checkip.data.IPListDao
import com.example.checkip.domain.IPSpamCheckUseCase
import com.example.checkip.extensions.launchWithErrorHandler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.*

class ListIPPresenter(
    private val IPListDao: IPListDao,
    private val FilterDao: FilterDao,
    private val ipSpamCheckUseCase: IPSpamCheckUseCase
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

    fun onIPRefresh(ip: IPPoint) {
        viewState.showLoading(isShow = true)
        presenterScope.launchWithErrorHandler(block = {
            IPListDao.replace(ipSpamCheckUseCase(ip.ip))
            ips = IPListDao.getAll()
            viewState.showIPs(applyFilter(ips))
            viewState.showLoading(isShow = false)
        }, onError = {
            viewState.errorAPI()
            viewState.showLoading(isShow = false)
        })
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

    @StateStrategyType(SkipStrategy::class)
    fun errorAPI()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(isShow: Boolean)

}