package com.example.checkip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_ip.*
import java.io.Serializable

class AddIPActivity : AppCompatActivity() {

    private var collection :MutableList<IPPoint> = mutableListOf<IPPoint>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ip)

        val input_collection = intent.extras?.getSerializable("CollectionIPPoints") as MutableList<IPPoint>?

        if (input_collection != null) {
            collection = input_collection
        }

        button_add_ip.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            val input1= if (ip_input_1.getText() != null) ip_input_1.getText().toString() else ""
            val input2 = if (ip_input_2.getText() != null) ip_input_2.getText().toString() else ""
            val input3 = if (ip_input_3.getText() != null) ip_input_3.getText().toString() else ""
            val input4 = if (ip_input_4.getText() != null) ip_input_4.getText().toString() else ""

            val ip = input1 + "." + input2 + "." + input3 + "." + input4;

            if(checkIP(ip)) {
                val ip_point = IPPoint(ip, "hostname", "")
                collection.add(ip_point)
                intent.putExtra("CollectionIPPoints", collection as Serializable)
                startActivity(intent)
            }
            else {
                text_message.text = "Invalid IP"
            }
        }
    }

    private fun checkIP(ip: String): Boolean {
        val tmp = ip.split('.')
        if (tmp.size != 4) {
            return false
        }
        else {
            for (i in tmp.indices) {
                if (tmp[i] != "") {
                    if (tmp[i].toInt() !in 0..255) {
                        return false
                    }
                }
                else return false
            }
        }
        return true
    }


}