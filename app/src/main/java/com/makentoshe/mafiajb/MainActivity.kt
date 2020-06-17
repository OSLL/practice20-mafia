package com.makentoshe.mafiajb

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var spinner: Spinner? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var itemList = arrayOf("4", "5", "6", "7", "8");
    private var counterChoose = "4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = Dialog(this);

        dialog.setContentView(R.layout.dialog)

        join.setOnClickListener {
            Log.d("main", "Intent to search!")
            var myIntent = Intent(this, Custom::class.java)

            myIntent.putExtra("cnt", counterChoose)

            finish()
            startActivity(myIntent)
        }

        exit.setOnClickListener {
            Log.d("main", "Intent to exit")
            finish()
        }

        instruction.setOnClickListener {
            dialog.show()
        }

        spinner = findViewById(R.id.spinners)
        arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, itemList)
        spinner?.adapter = arrayAdapter
        spinner?.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing select", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items: String = parent?.getItemAtPosition(position) as String
        counterChoose = items
    }
}
