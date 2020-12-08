package com.example.checkip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val collection = intent.extras?.getSerializable("CollectionIPPoints") as MutableList<IPPoint>?

        var text_list = ""

        if (collection != null) {
            collection.forEach { text_list += "{$it.ip}, {$it.hostname}, {$it.type}\n" }
        }

        if (text_list != "") {
            view_ip_list.text = text_list
        }

        button_add_new_ip.setOnClickListener {
            val intent = Intent(this, AddIPActivity::class.java)
            if (collection != null) {
                intent.putExtra("CollectionIPPoints", collection as Serializable)
            }
            startActivity(intent)
        }

    }
}