package com.example.checkip.feature.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.checkip.IPPoint
import com.example.checkip.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.ip_list_item.*

class ListIPAdapter(private val onIPClick: (IPPoint) -> Unit,
                    private val onIPDelete: (IPPoint) -> Unit) :
    ListAdapter<IPPoint, ListIPAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<IPPoint> () {
            override fun areItemsTheSame(oldItem: IPPoint, newItem: IPPoint): Boolean {
                return oldItem.ip == newItem.ip
            }

            override fun areContentsTheSame(oldItem: IPPoint, newItem: IPPoint): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ip_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: IPPoint = getItem(position)
        holder.containerView.setOnClickListener {
            onIPClick(item)
        }
        holder.ibDelete.setOnClickListener {
            onIPDelete(item)
        }
        holder.ipIP.text = item.ip
        holder.ipInList.text = if (item.in_list) "In BL" else " Not in BL"
        holder.ipNetworkType.text = item.network_type
        holder.ipCountry.text = item.country
        holder.ipLastActivity.text = item.last_activity
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}