package com.example.checkip.feature.list.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checkip.IPPoint
import com.example.checkip.R
import com.example.checkip.feature.list.presentation.ListIPPresenter
import com.example.checkip.feature.list.presentation.ListIPView
import com.example.checkip.ui.IPDetailFragment
import kotlinx.android.synthetic.main.fragment_ip_list.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListIPFragment : MvpAppCompatFragment(R.layout.fragment_ip_list), ListIPView {

    private var ListIPAdapter: ListIPAdapter? = null
    private val presenter: ListIPPresenter by moxyPresenter {
        ListIPPresenter()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ListIPAdapter = null
    }

    override fun showIPs(ips: List<IPPoint>) {
        ListIPAdapter?.submitList(ips)
    }

    override fun openDetailScreen(ip: IPPoint) {
        requireFragmentManager().beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .add(R.id.container, IPDetailFragment.newInstance(ip))
            .addToBackStack("IPDetailsFragment")
            .commit()
    }

    companion object {

        private const val IP = "IP"

        @JvmStatic
        fun newInstance() =
            ListIPFragment().apply {}
    }
}