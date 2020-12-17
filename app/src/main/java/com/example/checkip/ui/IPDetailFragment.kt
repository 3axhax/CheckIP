package com.example.checkip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.checkip.IPPoint
import com.example.checkip.R
import com.example.checkip.feature.list.ui.ListIPFragment
import kotlinx.android.synthetic.main.fragment_ip_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [IPDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IPDetailFragment : Fragment(R.layout.fragment_ip_detail) {

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

        detailBack.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .remove(this)
                .commit()
        }
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