package com.example.checkip.feature.add.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.checkip.R
import com.example.checkip.data.IPListDaoImpl
import com.example.checkip.di.cleantalkAPI
import com.example.checkip.domain.IPSpamCheckUseCase
import com.example.checkip.feature.add.presentation.AddIPPresenter
import com.example.checkip.feature.add.presentation.AddIPView
import com.example.checkip.feature.list.ui.ListIPFragment
import kotlinx.android.synthetic.main.fragment_ip_add.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AddIPFragment: MvpAppCompatFragment(R.layout.fragment_ip_add), AddIPView {

    private val presenter: AddIPPresenter by moxyPresenter {
        AddIPPresenter(
            IPListDao = IPListDaoImpl(
                sharedPreferences = requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
            ),
            IPSpamCheckUseCase(cleantalkAPI)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bAddIP.setOnClickListener {
            val input1= if (ip_input_1.text != null) ip_input_1.text.toString() else ""
            val input2 = if (ip_input_2.text != null) ip_input_2.text.toString() else ""
            val input3 = if (ip_input_3.text != null) ip_input_3.text.toString() else ""
            val input4 = if (ip_input_4.text != null) ip_input_4.text.toString() else ""

            val ip = "$input1.$input2.$input3.$input4";

            presenter.addIP(ip)
        }
    }

    override fun openIPList() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, ListIPFragment.newInstance())
            .addToBackStack("ListIPFragment")
            .commit()
    }

    override fun invalidIP() {
        Toast.makeText(requireContext(), "Invalid IP", Toast.LENGTH_LONG).show()
    }

    override fun existIP() {
        Toast.makeText(requireContext(), "This IP is exist", Toast.LENGTH_LONG).show()
    }

    override fun errorAPI() {
        Toast.makeText(requireContext(), "Error in API Request", Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isShow: Boolean) {
        progress.isVisible = isShow
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AddIPFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}