package com.example.checkip.feature.list.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkip.IPPoint
import com.example.checkip.R
import com.example.checkip.data.FilterDaoImpl
import com.example.checkip.data.IPListDaoImpl
import com.example.checkip.feature.add.ui.AddIPFragment
import com.example.checkip.feature.list.presentation.ListIPPresenter
import com.example.checkip.feature.list.presentation.ListIPView
import com.example.checkip.feature.detail.ui.IPDetailFragment
import com.example.checkip.feature.filter.ui.FilterIPFragment
import kotlinx.android.synthetic.main.fragment_ip_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListIPFragment : MvpAppCompatFragment(R.layout.fragment_ip_list), ListIPView {

    private var ListIPAdapter: ListIPAdapter? = null
    private val presenter: ListIPPresenter by moxyPresenter {
        ListIPPresenter(
            IPListDao = IPListDaoImpl(
                sharedPreferences = requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
            ),
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

        with(rvListIP) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ListIPAdapter(onIPClick = {
                presenter.onIPClick(it)
            }, onIPDelete = {
                presenter.onIPDelete(it)
            }).also {
                ListIPAdapter = it
            }
        }

        bFilter.setOnClickListener { presenter.onFilterClick() }
        bAdd.setOnClickListener { presenter.onAddClick() }
        bClearFilter.setOnClickListener { presenter.resetFilter() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ListIPAdapter = null
    }

    override fun showIPs(ips: List<IPPoint>) {
        ListIPAdapter?.submitList(ips)
    }

    override fun openDetailScreen(ip: IPPoint) {
        requireFragmentManager().fragments.forEach {
            if (it::class.simpleName == "IPDetailFragment") {
                requireFragmentManager().beginTransaction().setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                ).remove(it).commit()
            }
        }
        requireFragmentManager().beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .add(R.id.container, IPDetailFragment.newInstance(ip))
            .addToBackStack("IPDetailsFragment")
            .commit()
    }

    override fun openFilterScreen() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, FilterIPFragment.newInstance())
            .addToBackStack("FilterIPFragment")
            .commit()
    }

    override fun openAddScreen() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, AddIPFragment.newInstance())
            .addToBackStack("AddIPFragment")
            .commit()
    }

    override fun showClearFilter(show: Boolean) {
        bClearFilter.visibility = if (show) View.VISIBLE else View.GONE
    }

    companion object {

        private const val IP = "IP"

        @JvmStatic
        fun newInstance() =
            ListIPFragment().apply {}
    }
}