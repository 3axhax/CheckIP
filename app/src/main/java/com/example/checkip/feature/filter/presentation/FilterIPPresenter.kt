package com.example.checkip.feature.filter.presentation

import com.example.checkip.feature.list.presentation.ListIPPresenter
import moxy.MvpPresenter
import moxy.MvpView
import moxy.ktx.moxyPresenter
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class FilterIPPresenter : MvpPresenter<FilterIPView>() {

    fun applyFilter() {
        viewState.applyFilter()
    }

}

interface FilterIPView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun applyFilter()

}