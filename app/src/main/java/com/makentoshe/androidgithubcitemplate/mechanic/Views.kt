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
    fun setBtnActive(id : Int, prevId: Int)
    {
        arrayBtn[prevId].setBackgroundResource(R.drawable.circle_button)
        arrayBtn[id].setBackgroundResource(R.drawable.circle_button_active)
    }

    fun setBtnActive(id: Int) {
        arrayBtn[id].setBackgroundResource(R.drawable.circle_button_active)
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