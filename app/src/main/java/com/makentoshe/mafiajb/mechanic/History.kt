package com.makentoshe.mafiajb.mechanic

import android.view.View
import android.widget.ScrollView
import android.widget.TextView

class History(private var tv: TextView, private val scrollView: ScrollView) {
    fun write(text: String) {
        val message = this.tv.text.toString() + text + "\n"
        this.tv.text = message
        this.scrollView.fullScroll(View.FOCUS_DOWN)
    }
}