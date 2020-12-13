package com.example.checkip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checkip.R
import kotlinx.android.synthetic.main.fragment_ip_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [IPListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IPListFragment : Fragment(R.layout.fragment_ip_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_button_add_ip.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, IPAddFragment.newInstance())
                    .addToBackStack("IPListFragment")
                    .commit()
        }

        list_button_details.setOnClickListener {
            requireFragmentManager().beginTransaction()
                    .replace(R.id.container, IPDetailFragment.newInstance())
                    .addToBackStack("IPDetailFragment")
                    .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                IPListFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}