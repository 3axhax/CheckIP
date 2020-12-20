package com.example.checkip.feature.detail.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.checkip.IPPoint
import com.example.checkip.R
import com.example.checkip.feature.detail.presentation.IPDetailPresenter
import com.example.checkip.feature.detail.presentation.IPDetailView
import kotlinx.android.synthetic.main.fragment_ip_detail.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * A simple [Fragment] subclass.
 * Use the [IPDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IPDetailFragment : MvpAppCompatFragment(R.layout.fragment_ip_detail), IPDetailView {

    private val presenter: IPDetailPresenter by moxyPresenter {
        IPDetailPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val ip = it.getParcelable<IPPoint>(IP)
            tvIP.text = ip?.ip
        }

        detailBack.setOnClickListener { presenter.closeDetail() }

        ibCloseDetail.setOnClickListener { presenter.closeDetail() }
    }

    override fun closeDetail() {
        requireFragmentManager().beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .remove(this)
            .commit()
    }

    companion object {

        private const val IP = "IP"

        @JvmStatic
        fun newInstance(ip: IPPoint) =
            IPDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(IP, ip)
                }
            }
    }
}