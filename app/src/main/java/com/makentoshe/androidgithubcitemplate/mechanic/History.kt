package com.makentoshe.androidgithubcitemplate.mechanic

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.androidgithubcitemplate.R
import kotlinx.android.synthetic.main.game.*
import kotlin.concurrent.thread

class History(private var tv: TextView, private val scrollView: ScrollView) {
    fun write(text: String) {
        val message = this.tv.text.toString() + text + "\n"
        this.tv.text = message
        this.scrollView.fullScroll(View.FOCUS_DOWN)
    }
}