package com.makentoshe.androidgithubcitemplate

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = Dialog(this);

        dialog.setTitle("Instruction")
        dialog.setContentView(R.layout.dialog)

        join.setOnClickListener {
            Log.d("main", "Intent to search!")
            var myIntent = Intent(this, Custom::class.java)
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
    }
}
