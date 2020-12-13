package com.example.checkip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.checkip.R
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

        detail_button_ip_list.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, IPListFragment.newInstance())
                    .addToBackStack("IPListFragment")
                    .commit()
        }

        detail_button_add_new_ip.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, IPAddFragment.newInstance())
                    .addToBackStack("IPAddFragment")
                    .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            IPDetailFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}