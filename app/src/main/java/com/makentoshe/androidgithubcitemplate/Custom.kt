package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom.*

class Custom : AppCompatActivity() {
    //private var nameArray = Array<String>(6){""}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom)
        Log.d("search", "Intent to game!")

        nextBtn.setOnClickListener {
            val myIntent = Intent(this, Game::class.java)

            myIntent.putExtra("0", editText0.text.toString())
            myIntent.putExtra("1", editText1.text.toString())
            myIntent.putExtra("2", editText2.text.toString())
            myIntent.putExtra("3", editText3.text.toString())
            myIntent.putExtra("4", editText4.text.toString())
            myIntent.putExtra("5", editText5.text.toString())

            finish()
            startActivity(myIntent)
        }
    }
}
