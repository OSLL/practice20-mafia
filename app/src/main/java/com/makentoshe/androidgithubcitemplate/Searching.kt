package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.searching.*
import kotlin.concurrent.thread

class Searching : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searching)
        Log.d("search", "Intent to game!")

        /*thread {
            Thread.sleep(3000)
            var myIntent = Intent(this, Game::class.java)
            finish()
            startActivity(myIntent)
        }*/

        nextBtn.setOnClickListener {
            var myIntent = Intent(this, Game::class.java)
            finish()
            startActivity(myIntent)
        }
    }
}