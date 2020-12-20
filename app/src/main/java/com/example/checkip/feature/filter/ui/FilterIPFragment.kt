package com.example.checkip.feature.filter.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.checkip.R
import com.example.checkip.data.FilterDaoImpl
import com.example.checkip.feature.filter.presentation.FilterIPPresenter
import com.example.checkip.feature.filter.presentation.FilterIPView
import com.example.checkip.feature.list.ui.ListIPFragment
import kotlinx.android.synthetic.main.fragment_ip_filter.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FilterIPFragment : MvpAppCompatFragment(R.layout.fragment_ip_filter), FilterIPView {

    private val presenter: FilterIPPresenter by moxyPresenter {
        FilterIPPresenter(
            FilterDao = FilterDaoImpl(
                sharedPreferences = requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bApplyFilter.setOnClickListener {
            val search = if (etSearchString.text != null) etSearchString.text.toString() else ""
            presenter.applyFilter(search)
        }

    }

    override fun applyFilter() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, ListIPFragment.newInstance())
            .addToBackStack("ListIPFragment")
            .commit()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FilterIPFragment().apply {
                arguments = Bundle().apply {}
            }
    }


}