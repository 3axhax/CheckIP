package com.example.checkip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.checkip.R
import kotlinx.android.synthetic.main.fragment_ip_add.*

/**
 * A simple [Fragment] subclass.
 * Use the [IPAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IPAddFragment : Fragment(R.layout.fragment_ip_add) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*add_button_details.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, IPDetailFragment.newInstance())
                    .addToBackStack("IPDetailFragment")
                    .commit()
        }

        add_button_add_ip.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, IPListFragment.newInstance())
                    .addToBackStack("IPListFragment")
                    .commit()
        }*/
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            IPAddFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}