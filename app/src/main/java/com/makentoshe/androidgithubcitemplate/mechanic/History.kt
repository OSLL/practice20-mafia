package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.androidgithubcitemplate.R
import kotlinx.android.synthetic.main.game.*

class History(private var tv: TextView) {
    fun write(text: String) {
        val message = this.tv.text.toString() + text + "\n"
        this.tv.text = message
    }
}