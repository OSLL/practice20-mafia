package com.makentoshe.androidgithubcitemplate.mechanic

import android.view.View
import android.annotation.SuppressLint
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.makentoshe.androidgithubcitemplate.R

class Views(private val arrayBtn: Array<Button>, private val arrayPm: ArrayList<PopupMenu>,
            private val icon: ImageView, private val state: TextView,
            private val exit: Button, private val start: Button) {
    var skinUsual = arrayOf(R.drawable.circle_button0, R.drawable.circle_button1, R.drawable.circle_button2, R.drawable.circle_button3, R.drawable.circle_button4, R.drawable.circle_button5)
    var skinActive = arrayOf(R.drawable.circle_button_active0, R.drawable.circle_button_active1, R.drawable.circle_button_active2, R.drawable.circle_button_active3, R.drawable.circle_button_active4, R.drawable.circle_button_active5)

    fun setBtnActive(id : Int, prevId: Int)
    {
        arrayBtn[prevId].setBackgroundResource(skinUsual[prevId])
        arrayBtn[id].setBackgroundResource(skinActive[id])
    }

    fun setBtnActive(id: Int) {
        arrayBtn[id].setBackgroundResource(skinActive[id])
    }

    fun blockPopups() {

    }

    fun setPopupInactive() {

    }

    fun allPopups() {

    }

    fun restorePopup(id: Int) {
        arrayBtn[id].setOnClickListener {arrayPm[id].show()}
    }

    fun changeStateText (text: String) {
        state.text = text
    }

    fun setIcon(name: String) {
        when (name) {
            "moon" -> icon.setImageResource(R.drawable.moon)
            "sun" -> icon.setImageResource(R.drawable.sun)
        }
    }

    fun setPlayerDie(id: Int) {
        arrayBtn[id].setBackgroundResource(R.drawable.circle_button_die)
        arrayBtn[id].setOnClickListener(null)
    }

    fun showExit() {
        start.visibility = View.INVISIBLE
        exit.visibility = View.VISIBLE
    }

    fun cntInit(id: Int) {
        arrayBtn[id].text = "0"
    }

    @SuppressLint("SetTextI18n")
    fun cntIncrement(id: Int) {
        arrayBtn[id].text = "${arrayBtn[id].text.toString().toInt() + 1}"
    }

    fun cntsDel() {
        for (btn in arrayBtn)
            btn.text = ""
    }
}