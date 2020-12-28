package com.example.checkip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checkip.data.IPListDaoImpl
import com.example.checkip.domain.IPPoint
import com.example.checkip.feature.list.ui.ListIPFragment


class MainActivity : AppCompatActivity() {

    private var ips = listOf(
        IPPoint("101.51.0.139"),
        IPPoint("144.76.74.253"),
        IPPoint("46.166.143.108"),
        IPPoint("5.188.84.95"),
        IPPoint("1.1.1.1")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val IPListDaoImpl = IPListDaoImpl(getSharedPreferences("data", Context.MODE_PRIVATE))

        ips.forEach {
            IPListDaoImpl.add(it)
        }

        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
                .add(R.id.container, ListIPFragment())
                .commit()

    }
}