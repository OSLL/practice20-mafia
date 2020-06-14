package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Custom : AppCompatActivity() {
    lateinit var arrayEdit: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom)
        Log.d("search", "Intent to game!")

        arrayEdit = arrayOf(editText0, editText1, editText2, editText3,
            editText4, editText5, editText6, editText7)
        val playerCounter = intent.getStringExtra("cnt").toString()

        for (i in 0 until playerCounter.toInt())
            arrayEdit[i].visibility = View.VISIBLE

        backBtn.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(myIntent)
        }

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
