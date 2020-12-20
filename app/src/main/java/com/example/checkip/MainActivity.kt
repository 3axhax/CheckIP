package com.example.checkip

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checkip.data.IPListDaoImpl
import com.example.checkip.feature.list.ui.ListIPFragment


class MainActivity : AppCompatActivity() {

    private var ips = listOf(
        IPPoint("1.1.1.1"),
        IPPoint("2.2.3.4"),
        IPPoint("2.5.7.9"),
        IPPoint("3.2.3.4"),
        IPPoint("250.26.33.40"),
        IPPoint("25.27.44.50"),
        IPPoint("77.56.89.99"),
        IPPoint("88.6.9.7"),
        IPPoint("79.4.86.67"),
        IPPoint("65.23.16.100"),
        IPPoint("6.5.123.54"),
        IPPoint("1.1.1.1"),
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