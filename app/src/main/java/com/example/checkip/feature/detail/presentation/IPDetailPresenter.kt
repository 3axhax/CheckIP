package com.example.checkip.feature.detail.presentation

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

class IPDetailPresenter : MvpPresenter<IPDetailView>() {

    fun closeDetail() {
        viewState.closeDetail()
    }

}

interface IPDetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun closeDetail()

}