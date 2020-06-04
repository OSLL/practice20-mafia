package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        join.setOnClickListener {
            Log.d("main", "Intent to search!")
            var myIntent = Intent(this, Searching::class.java)
            finish()
            startActivity(myIntent)
        }
    }
}
